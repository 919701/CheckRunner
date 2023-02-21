package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @MockBean
    private final ProductRepositories repositories;

    @InjectMocks
    private final ProductServiceImpl service;


    @Test
    void findAllProducts() {
        when(repositories.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, service::findAllProducts);
        verify(repositories).findAll();
    }

    @Test
    void findById() {
        when(repositories.findById(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> service.findById(1L));
        verify(repositories).findById(any());
    }

    @Test
    void saveProduct() {
        when(repositories.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        Product product = new Product(1L, "Name", 10d, true);
        assertThrows(ResponseStatusException.class, () -> service.saveProduct(product));
        verify(repositories).save(any());
    }

    @Test
    void deleteProduct() {
        doNothing().when(repositories).deleteById(any());
        service.deleteProduct(1L);
        verify(repositories).deleteById(any());
    }

    @Test
    void updateProduct() {
        Product product = Product.builder()
                .id(1L)
                .title("productTest")
                .price(10d)
                .discount(true)
                .build();
        when(repositories.findById(any())).thenReturn(Optional.ofNullable(product));
        assertDoesNotThrow(() -> service.updateProduct(1L, product));
        verify(repositories).findById(any());
    }
}