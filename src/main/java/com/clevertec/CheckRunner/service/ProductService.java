package com.clevertec.CheckRunner.service;

import com.clevertec.CheckRunner.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findById(long id);

    boolean saveProduct(Product product);

    boolean deleteProduct(Long id);

    boolean updateProduct(Long id, Product product);
}
