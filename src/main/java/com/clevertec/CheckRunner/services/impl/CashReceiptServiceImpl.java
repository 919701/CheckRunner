package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.models.CashReceipt;
import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.services.DiscountCardService;
import com.clevertec.CheckRunner.services.CashReceiptService;
import com.clevertec.CheckRunner.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CashReceiptServiceImpl implements CashReceiptService {

    private final ProductService productService;
    private final DiscountCardService discountCardService;

    @Override
    public CashReceipt buildReceipt(String[] args) {
        Map<Product, Double> products = new HashMap<>();
        DiscountCard discountCard = null;

        for (String arg : args) {
            String[] separatedArg = arg.split("-");
            if (arg.contains("card")) {
                discountCard = discountCardService.findByNumberCard(Integer.parseInt(separatedArg[1]));
            } else {
                Product product = productService.findById(Long.parseLong(separatedArg[0]));
                products.put(product, Double.parseDouble(separatedArg[1]));
            }
        }
        return CashReceipt.builder()
                .products(products)
                .discountCard(discountCard)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public CashReceipt buildReceipt(List<String> items, Integer discountCardNumber) {
        Map<Product, Double> products = new HashMap<>();
        DiscountCard discountCard = (discountCardNumber != null)
                ? discountCardService.findByNumberCard(discountCardNumber)
                : null;
        items.forEach(item -> {
            String[] separateArgs = item.split("-");
            Product product = productService.findById(Long.parseLong(separateArgs[0]));
            products.put(product, Double.parseDouble(separateArgs[1]));
        });

        return CashReceipt.builder()
                .products(products)
                .discountCard(discountCard)
                .dateTime(LocalDateTime.now())
                .build();
    }
}

