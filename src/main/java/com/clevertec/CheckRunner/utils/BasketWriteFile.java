package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.print.BasketPrint;
import com.clevertec.CheckRunner.models.Basket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileWriter;
import java.io.IOException;

@Data
@AllArgsConstructor
public class BasketWriteFile implements BasketWrite {

    private final BasketWriteConsole consoleCashReceiptWrite;
    private final BasketPrint basketPrint;

    public void write(Basket basket) {
        try {
            FileWriter fileWriter = new FileWriter("CashReceipt.txt");
            consoleCashReceiptWrite.write(basket);
            fileWriter.write(basketPrint.printCheck(basket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
