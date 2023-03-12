package com.clevertec.CheckRunner.service;

import com.clevertec.CheckRunner.model.Basket;
import com.clevertec.CheckRunner.model.Product;

import java.util.List;
import java.util.Map;

public interface BasketService {

    Basket buildBasket(String[] args);
    Basket buildBasket(List<String> item, Integer discountCardNumber);

    Map<Product, Double> coastProducts(Basket basket);

    Double totalWithoutDiscount(Basket basket);

    Double totalWithDiscount(Basket basket);

}
