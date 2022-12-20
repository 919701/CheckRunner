package com.clevertec.CheckRunner.utils.write;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.utils.mapper.BasketToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@Data
@AllArgsConstructor
public class BasketWriteFile implements BasketWrite {

    private final BasketWriteConsole consoleCashReceiptWrite;
    private final BasketToString basketToString;

    public void write(Basket basket) {
        try {
            PrintWriter fileWriter = new PrintWriter("CashReceipt.txt");
            consoleCashReceiptWrite.write(basket);
            fileWriter.write(basketToString.printCheck(basket));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
