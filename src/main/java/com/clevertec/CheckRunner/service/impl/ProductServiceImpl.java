package com.clevertec.CheckRunner.service.impl;

import com.clevertec.CheckRunner.exeption.ProductNotFoundException;
import com.clevertec.CheckRunner.model.Product;
import com.clevertec.CheckRunner.repositorie.ProductRepositories;
import com.clevertec.CheckRunner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepositories productRepositories;

    public List<Product> findAllProducts() {
        return productRepositories.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepositories.findById(id);
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
