package com.clevertec.CheckRunner.aop.aspects;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductAspect {

    @Before("execution(* com.clevertec.CheckRunner.services.ProductService.findById(..))")
    public void beforeGetProductIdAdvice(){
        System.out.println("beforeGetProductIDAdvice: GET PRODUCT ID --------------------------------------");
    }
}
