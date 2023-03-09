package com.clevertec.CheckRunner.aop.aspects;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.services.impl.LRUCacheImpl;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
@AllArgsConstructor
public class ProductAspect {

    private final LRUCacheImpl cache;


    @Around("execution(* com.clevertec.CheckRunner.services.ProductService.findById(..))")
    public Optional<Product> aroundGetProductIdAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        var id = (Long) joinPoint.getArgs()[0];
        var cacheProduct = cache.get(id);
//        System.out.println("-".repeat(60) + "\n" + cacheProduct.isPresent());
//        if (cacheProduct.isEmpty()) {
//            var product = (Product) joinPoint.proceed();
//            cache.put(id, product);
//            return Optional.ofNullable(product);
//        }

        return (Optional<Product>) joinPoint.proceed();
    }
}
