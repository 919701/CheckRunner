package com.clevertec.CheckRunner.utils.write;

import com.clevertec.CheckRunner.utils.mapper.BasketToString;
import com.clevertec.CheckRunner.models.Basket;
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
