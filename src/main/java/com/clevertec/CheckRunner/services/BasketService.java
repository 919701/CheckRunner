package com.clevertec.CheckRunner.services;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.models.Product;

import java.util.List;
import java.util.Map;

public interface BasketService {

    Basket buildBasket(String[] args);
    Basket buildBasket(List<String> item, Integer discountCardNumber);

    Map<Product, Double> coastProducts(Basket basket);

    Double totalWithoutDiscount(Basket basket);

    Double totalWithDiscount(Basket basket);

}
