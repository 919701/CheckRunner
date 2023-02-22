package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private  ProductRepositories productRepositories;

    @InjectMocks
    private ProductServiceImpl productService;

    List<Product> products = List.of(
            new Product(1L, "productTest1", 10.0, false),
            new Product(2L, "productTest2", 20.0, false),
            new Product(3L, "productTest3", 30.0, true),
            new Product(4L, "productTest4", 40.0, false)
    );

    @Test
    void findAllProducts() {
    }

    @Test
    void findById() {
    }

    @Test
    void saveProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }
}