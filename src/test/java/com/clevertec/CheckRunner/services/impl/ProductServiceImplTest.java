package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        doReturn(products).when(productRepositories).findAll();
        List<Product> productList = productService.findAllProducts();
        assertEquals(products, productList);
        verify(productRepositories).findAll();
    }

    @Test
    void findById() {
        doReturn(Optional.ofNullable(products.get(1))).when(productRepositories).findById(2L);
        Optional<Product> productTest = productService.findById(2L);
        assertEquals( Optional.ofNullable(products.get(1)), productTest);
        verify(productRepositories).findById(any());
    }

    @Test
    void saveProduct() {
        assertTrue(productService.saveProduct(any()));
        verify(productRepositories).save(any());

    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepositories).deleteById(any());
        assertTrue(productService.deleteProduct(any()));
        verify(productRepositories).deleteById(any());
    }

    @Test
    void updateProduct() {
        Product productUpdate = new Product(123L, "productDelete", 10.0, false);
        doReturn(Optional.of(productUpdate)).when(productRepositories).findById(any());
        assertDoesNotThrow(() -> productService.updateProduct(any(), productUpdate));
        verify(productRepositories).findById(any());
    }
}