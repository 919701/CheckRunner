package com.clevertec.CheckRunner.util.write;

import com.clevertec.CheckRunner.util.mapper.BasketToString;
import com.clevertec.CheckRunner.model.Basket;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class BasketWriteConsole implements BasketWrite {

    private final BasketToString basketToString;

    @Override
    public void write(Basket basket) {
        System.out.println(basketToString.printCheck(basket));
    }
}
