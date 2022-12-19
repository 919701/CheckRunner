package com.clevertec.CheckRunner.interpretations;

import com.clevertec.CheckRunner.models.CashReceipt;
import com.clevertec.CheckRunner.utils.CashReceiptCalculation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class CashReceiptToString implements CashReceiptToPrint {

//    private final CashReceiptCalculation calculation;

    @Override
    public String printCashReceipt(CashReceipt cashReceipt) {
        StringBuilder check = new StringBuilder();

        check.append(String.format("\n" + "=".repeat(40) + "\n%26s\n%28s\n%33s\n%28s\n\n%36s\n%34s" + "\n" + "-".repeat(40) + "\n%4s%18s%8s%8s\n",
                "CASH RECEIPT",
                "SUPERMARKET 1234",
                "12. MILKYWAY Galaxy/ Earth",
                "Tel: 123-456-789",
                "Cashier: Ivanov DATE:" + cashReceipt.getDateTime().toLocalDate(),
                "TIME:" + DateTimeFormatter.ofPattern("HH:mm:ss").format(cashReceipt.getDateTime()),
                "QTY", "DESCRIPTION", "PRICE", "TOTAL"
        ));


        return check.toString();
    }
}
