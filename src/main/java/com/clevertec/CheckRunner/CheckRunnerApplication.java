package com.clevertec.CheckRunner;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.services.BasketService;
import com.clevertec.CheckRunner.utils.BasketWriteConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckRunnerApplication {


    public static void main(String[] args) {

        ApplicationContext context =  SpringApplication.run(CheckRunnerApplication.class, args);

        Basket basket = context.getBean(BasketService.class).buildBasket(args);
        context.getBean(BasketWriteConsole.class).write(basket);

    }

}
