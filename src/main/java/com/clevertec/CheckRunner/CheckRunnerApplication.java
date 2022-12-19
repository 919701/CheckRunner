package com.clevertec.CheckRunner;

import com.clevertec.CheckRunner.models.CashReceipt;
import com.clevertec.CheckRunner.services.CashReceiptService;
import com.clevertec.CheckRunner.utils.ConsoleCashReceiptWrite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CheckRunnerApplication {


    public static void main(String[] args) {

        ApplicationContext context =  SpringApplication.run(CheckRunnerApplication.class, args);

        CashReceipt cashReceipt = context.getBean(CashReceiptService.class).buildReceipt(args);
        context.getBean(ConsoleCashReceiptWrite.class).write(cashReceipt);

    }

}
