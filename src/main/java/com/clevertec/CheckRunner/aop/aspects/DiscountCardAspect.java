package com.clevertec.CheckRunner.aop.aspects;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;
import com.clevertec.CheckRunner.cache.factoryCache.CacheTypeMethod;
import com.clevertec.CheckRunner.model.DiscountCard;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@AllArgsConstructor
public class DiscountCardAspect {
    private final Cache<Integer, DiscountCard> cache =
            new CacheFactory().getCacheMethod(4, CacheTypeMethod.LRU);

    private final Cache<Long, Optional<DiscountCard>> cache2 =
            new CacheFactory().getCacheMethod(4, CacheTypeMethod.LRU);


    @Around("execution(* com.clevertec.CheckRunner.service.DiscountCardService.findByNumberCard(..))")
    public DiscountCard aroundGetDiscountCardFindByNumberCard(ProceedingJoinPoint joinPoint) throws Throwable {

        var number = (Integer) joinPoint.getArgs()[0];
        var cacheCard = cache.get(number);

        if (cacheCard == null) {
            var card = (DiscountCard) joinPoint.proceed();
            cache.put(number, card);
            return cache.get(number);
        }
        return cacheCard;
    }

    @Around("execution(* com.clevertec.CheckRunner.service.DiscountCardService.findById())")
    public Optional<DiscountCard> aroundGetDiscountCardFindById(ProceedingJoinPoint joinPoint) {

        var id = (Long) joinPoint.getArgs()[0];
        var cacheDiscountCard = cache2.get(id);

        if (cacheDiscountCard == null) {
            try {
                var discountCard = (Optional<DiscountCard>) joinPoint.proceed();
                cache2.put(id, discountCard);
                return cache2.get(id);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return cacheDiscountCard;
    }


}
