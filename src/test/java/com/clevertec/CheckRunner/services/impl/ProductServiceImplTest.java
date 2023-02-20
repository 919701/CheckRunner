package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @MockBean
    private final ProductRepositories productRepositories;

    @InjectMocks
    private final ProductServiceImpl productServiceImpl;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void findAllProducts() {
    }

    @Test
    void findById() {
        when(productRepositories.findById(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
//        assertThrows(expectedType:ResponseStatusException.class, () -> productServiceImpl.findById(1L));
        verify(productRepositories.findById(any()));

    }

    @Test
    @DisplayName("Test save product in ProductServiceImpl.class:")
    void saveProduct() {
        when(productRepositories.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        Product product = new Product(1L, "Name", 10d, true);
        assertThrows(ResponseStatusException.class, () -> productServiceImpl.saveProduct(product));
        verify(productRepositories).save(any());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepositories).deleteById(any());
        productServiceImpl.deleteProduct(1L);
        verify(productRepositories).deleteById(any());
    }

    @Test
    void updateProduct() {
    }
}