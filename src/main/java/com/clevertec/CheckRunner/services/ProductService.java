package com.clevertec.CheckRunner.services;

import com.clevertec.CheckRunner.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findById(long id);

    boolean saveProduct(Product product);

    boolean deleteProduct(Long id);

    boolean updateProduct(Long id, Product product);
}
