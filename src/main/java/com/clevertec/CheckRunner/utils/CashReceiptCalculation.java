package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.models.CashReceipt;
import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.services.DiscountCardService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;


@Data
@AllArgsConstructor
public class CashReceiptCalculation {

    private final CashReceipt cashReceipt;
    private final DiscountCardService discountCardService;
    private Double totalWithoutDiscount;
    private Double totalWithDiscount;

    public Map<Product, Double> coastProducts() {

        final Double discountPercent = Double.valueOf(10); //Процент скидки
        final String DISCOUNT_MESSAGE = " (discount " + discountPercent + ")";

        //Если в чеке больше пяти шт. аукционных товаров, то скидка 10% по этой позиции
        cashReceipt.getProducts().forEach((product, quantity) -> {
            if (quantity > 5 && product.getDiscount()) {
                product.setTitle(product.getTitle() + DISCOUNT_MESSAGE);
                coastProducts().put(product, product.getPrice() * quantity * (100 - discountPercent) / 100);
            } else {
                coastProducts().put(product, product.getPrice() * quantity);
            }
        });
        return coastProducts();
    }

    public void totalCash() {
        for (Map.Entry<Product, Double> entry : coastProducts().entrySet()) {
            totalWithoutDiscount += entry.getValue();
        }

        if (discountCardService.isDiscountCard(cashReceipt.getDiscountCard())) {
            totalWithDiscount = totalWithoutDiscount * (1 - cashReceipt.getDiscountCard().getDiscountPercent());
        }
    }
}
