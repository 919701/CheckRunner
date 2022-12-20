package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.exeption.ProductNotFoundException;
import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import com.clevertec.CheckRunner.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepositories productRepositories;

    public List<Product> findAllProducts() {
        return productRepositories.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepositories.findById(id).orElse(null);
    }

    @Transactional
    public boolean saveProduct(Product product) {
        productRepositories.save(product);
        return true;
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        productRepositories.deleteById(id);
        return true;
    }

    @Transactional
    public boolean updateProduct(Long id, Product product) {
        productRepositories.findById(id)
                .map(updateProduct -> {
                    updateProduct.setTitle(product.getTitle());
                    updateProduct.setPrice(product.getPrice());
                    updateProduct.setDiscount(product.getDiscount());
                    return updateProduct;
                }).orElseThrow(() ->
                        new ProductNotFoundException("Product non found in this id: " + id));
        return true;
    }

}
