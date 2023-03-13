package com.clevertec.CheckRunner.aop.aspects;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;
import com.clevertec.CheckRunner.cache.factoryCache.CacheTypeMethod;
import com.clevertec.CheckRunner.model.Product;
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

    private final Cache<Long, Optional<Product>> cache =
            new CacheFactory().getCacheMethod(5, CacheTypeMethod.LRU);

    @Around("execution(* com.clevertec.CheckRunner.service.ProductService.findById(..))")
    public Optional<Product> aroundGetProductIdAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        var id = (Long) joinPoint.getArgs()[0];
        var cacheProduct = cache.get(id);

        if (cacheProduct == null) {
            var product = (Optional<Product>) joinPoint.proceed();
            cache.put(id, product);
            return cache.get(id);
        }
        return cacheProduct;
    }

    @Around("execution(* com.clevertec.CheckRunner.service.ProductService.deleteProduct(..))")
    public Boolean aroundDeleteProductById(ProceedingJoinPoint joinPoint) throws Throwable {

        var id = (Long) joinPoint.getArgs()[0];
        joinPoint.proceed();
        if (cache.get(id) != null) {
            return cache.remove(id);
        }
        return true;
    }

    @Around("execution(* com.clevertec.CheckRunner.service.ProductService.saveProduct(..))")
    public boolean aroundSaveProductById(ProceedingJoinPoint joinPoint) throws Throwable {

        var product = (Product) joinPoint.getArgs()[0];
        joinPoint.proceed();
        cache.put(product.getId(), Optional.of(product));
        return true;
    }

    public boolean aroundUpdateProduct(ProceedingJoinPoint joinPoint) {
        var product = (Product) joinPoint.getArgs()[0];
        var id = (Long) product.getId();
        if (cache.get(id) != null) {
            cache.put(id,Optional.of(product));
            return true;
        }
        return true;
    }
}
