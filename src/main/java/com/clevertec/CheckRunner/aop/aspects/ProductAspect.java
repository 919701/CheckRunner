package com.clevertec.CheckRunner.aop.aspects;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;
import com.clevertec.CheckRunner.cache.factoryCache.CacheTypeMethod;
import com.clevertec.CheckRunner.models.Product;
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

    private final Cache<Long, Optional<Product>> cache = new CacheFactory().getCacheMethod(CacheTypeMethod.LRU, 10);


//    @Around("execution(* com.clevertec.CheckRunner.services.ProductService.findById(..))")
//    public Optional<Product> aroundGetProductIdAdvice(ProceedingJoinPoint joinPoint) {

//        var id = (Long) joinPoint.getArgs()[0];
//        System.out.println("Request id product:" + id);
//
//        var cacheProduct =  cache.get(id);
//        System.out.println("cacheProduct: " + cacheProduct);
//
//        if (cache.get(id).isEmpty()) {
//            try {
//                var product = (Optional<Product>) joinPoint.proceed();
//                cache.put(id, product);
//                return cache.get(id);
//            } catch (Throwable e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return cacheProduct;
//    }
}
