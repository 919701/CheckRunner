package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.services.BasketService;
import com.clevertec.CheckRunner.services.DiscountCardService;
import com.clevertec.CheckRunner.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final ProductService productService;
    private final DiscountCardService discountCardService;

    @Override
    @SneakyThrows
    public Basket buildBasket(String[] args) {
        Map<Product, Double> products = new HashMap<>();
        DiscountCard discountCard = null;

        for (String arg : args) {
            String[] separatedArg = arg.split("-");
            if (arg.contains("card")) {
                discountCard = discountCardService.findByNumberCard((Integer.parseInt(separatedArg[1])));
            } else {
                Product product = productService.findById(Long.parseLong(separatedArg[0]));
                if (product != null) {
                    products.put(product, Double.parseDouble(separatedArg[1]));
                }
            }
        }
        return Basket.builder()
                .products(products)
                .discountCard(discountCard)
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public Basket buildBasket(List<String> items, Integer discountCardNumber) {
        Map<Product, Double> products = new HashMap<>();
        DiscountCard discountCard = discountCardService.findByNumberCard(discountCardNumber);
        items.forEach(item -> {
            String[] separateArgs = item.split("-");
            Product product = productService.findById(Long.parseLong(separateArgs[0]));
            if (product != null) {
                products.put(product, Double.parseDouble(separateArgs[1]));
            }
        });

        return Basket.builder()
                .products(products)
                .discountCard(discountCard)
                .dateTime(LocalDateTime.now())
                .build();
    }

    public Map<Product, Double> coastProducts(Basket basket) {
        Map<Product, Double> coast = new HashMap<>();
        final double DISCOUNT_PERCENT = 10.0; //Процент скидки

        //Если в чеке больше пяти шт. аукционных товаров, то скидка 10% по этой позиции
        basket.getProducts().forEach((product, quantity) -> {
            if (quantity > 5 && product.getDiscount()) {
                coast.put(product, product.getPrice() * quantity * (100 - DISCOUNT_PERCENT) / 100);
            } else {
                coast.put(product, product.getPrice() * quantity);
            }
        });
        return coast;
    }

    public Double totalWithoutDiscount(Basket basket) {
        Double total = (double) 0;
        Map<Product, Double> productDoubleMap = coastProducts(basket);
        for (Map.Entry<Product, Double> entry : productDoubleMap.entrySet()) {
            Double coast = entry.getValue();
            total += coast;
        }
        return total;
    }

    @SneakyThrows
    public Double totalWithDiscount(Basket basket) {

        return (basket.getDiscountCard() != null)
                ? totalWithoutDiscount(basket) * (100 - basket.getDiscountCard().getDiscountPercent()) / 100
                : totalWithoutDiscount(basket);
    }
}
