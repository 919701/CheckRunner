package com.clevertec.CheckRunner;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.services.BasketService;
import com.clevertec.CheckRunner.utils.write.BasketWriteFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckRunnerApplication {


    public static void main(String[] args) {

        ApplicationContext context =  SpringApplication.run(CheckRunnerApplication.class, args);

        var basket = context.getBean(BasketService.class).buildBasket(args);
        context.getBean(BasketWriteFile.class).write(basket);
    }

}
