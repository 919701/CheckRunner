package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.interpretations.CashReceiptToPrint;
import com.clevertec.CheckRunner.models.CashReceipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ConsoleCashReceiptWrite implements CashReceiptWrite {

    private final CashReceiptToPrint cashReceiptToPrint;

    @Override
    public void write(CashReceipt cashReceipt) {
        System.out.println(cashReceiptToPrint.printCashReceipt(cashReceipt));
    }
}
