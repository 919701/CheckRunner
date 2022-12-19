package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.interpretations.PrintCashReceipt;
import com.clevertec.CheckRunner.models.CashReceipt;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileWriter;
import java.io.IOException;

@Data
@AllArgsConstructor
public class FileCashReceiptWrite implements CashReceiptWrite {

    private final ConsoleCashReceiptWrite consoleCashReceiptWrite;
    private final PrintCashReceipt printCashReceipt;

    public void write(CashReceipt cashReceipt) {
        try {
            FileWriter fileWriter = new FileWriter("CashReceipt.txt");
            consoleCashReceiptWrite.write(cashReceipt);
            fileWriter.write(printCashReceipt.printCashReceipt(cashReceipt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
