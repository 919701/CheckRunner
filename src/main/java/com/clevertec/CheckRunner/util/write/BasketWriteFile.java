package com.clevertec.CheckRunner.util.write;

import com.clevertec.CheckRunner.model.Basket;
import com.clevertec.CheckRunner.util.mapper.BasketToString;
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
    private final String FILE_NAME = "CashReceipt.txt";
    public void write(Basket basket) {

        try (PrintWriter fileWriter = new PrintWriter(FILE_NAME)) {
            consoleCashReceiptWrite.write(basket);
            fileWriter.write(basketToString.printCheck(basket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
