package com.clevertec.CheckRunner.utils.mapper;

import com.clevertec.CheckRunner.exeption.DiscountCardNotFoundException;
import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.services.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@AllArgsConstructor
public class BasketToStringImpl implements BasketToString {

    private final BasketService basketService;

    @Override
    public String printCheck(Basket basket) throws DiscountCardNotFoundException {
        StringBuilder check = new StringBuilder();
        Map<Product, Double> coast = basketService.coastProducts(basket);

        check.append(String.format("\n%31s\n%33s\n%40s\n%34s\n\n%-26s%34s\n%60s\n%s\n"
                        + "-".repeat(60) + "\n%4s%28s%15s%12s\n",
                "CASH RECEIPT",
                "SUPERMARKET 123",
                "12. MILKYWAY Galaxy/ Earth",
                "Tel: 123-456-70000",
                "Cashier: #1520", "DATE:" + basket.getDateTime().toLocalDate(),
                "TIME:" + DateTimeFormatter.ofPattern("HH:mm:ss").format(basket.getDateTime()),
                (basket.getDiscountCard() != null)
                        ? "DISCOUNT CARD: " + basket.getDiscountCard().getNumberCard()
                        : "DISCOUNT CARD: not found",
                "QTY", "DESCRIPTION", "PRICE", "TOTAL"
        ));
        coast.forEach((product, count) -> check.append(String.format("%3.0f   %-35s" + "$ %-10.2f" + "$ %-10.2f\n",
                basket.getProducts().get(product),
                product.getTitle() + (product.getDiscount() ? " (discount 10%)" : ""),
                product.getPrice(),
                count)));

        check.append(String.format("=".repeat(60) + "\nTAXABLE TOT. %47.2f\nVAT %2.2f %% %49.2f\nTOTAL%55.2f",
                basketService.totalWithoutDiscount(basket),
                (basket.getDiscountCard() != null)
                        ? basket.getDiscountCard().getDiscountPercent()
                        : 0,
                basketService.totalWithoutDiscount(basket) - basketService.totalWithDiscount(basket),
                basketService.totalWithDiscount(basket)));

        return check.toString();
    }
}
