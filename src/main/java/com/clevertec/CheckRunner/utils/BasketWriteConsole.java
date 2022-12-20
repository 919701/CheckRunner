package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.print.BasketPrint;
import com.clevertec.CheckRunner.models.Basket;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class BasketWriteConsole implements BasketWrite {

    private final BasketPrint basketPrint;

    @Override
    public void write(Basket basket) {
        System.out.println(basketPrint.printCheck(basket));
    }
}
