package com.clevertec.CheckRunner.utils;

import com.clevertec.CheckRunner.interpretations.PrintCashReceipt;
import com.clevertec.CheckRunner.models.CashReceipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ConsoleCashReceiptWrite implements CashReceiptWrite {

    private final PrintCashReceipt printCashReceipt;

    @Override
    public void write(CashReceipt cashReceipt) {
        System.out.println(printCashReceipt.printCashReceipt(cashReceipt));
    }
}
