package com.clevertec.CheckRunner.service.impl;

import com.clevertec.CheckRunner.model.Product;
import com.clevertec.CheckRunner.repositorie.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.clevertec.CheckRunner.util.impl.ProductTestBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepositories productRepositories;

    @InjectMocks
    private ProductServiceImpl productService;

    final List<Product> products = List.of(
            aProduct().build(),
            aProduct()
                    .withId(2L)
                    .build()
    );

    @Test
    void checkFindAllProductsShouldListProducts() {

        doReturn(products).when(productRepositories).findAll();

        final var productList = productService.findAllProducts();

        assertEquals(products, productList);
        verify(productRepositories).findAll();
    }

    @Test
    void checkFindByIdShouldProduct() {

        doReturn(Optional.ofNullable(products.get(1))).when(productRepositories).findById(2L);

        final var productTest = productService.findById(2L);

        assertEquals(Optional.ofNullable(products.get(1)), productTest);
        verify(productRepositories).findById(any());
    }

    @Test
    void checkSaveProductShouldTrue() {

        assertTrue(productService.saveProduct(any()));
        verify(productRepositories).save(any());
    }

    @Test
    void checkDeleteProductShouldTrue() {

        doNothing().when(productRepositories).deleteById(any());

        assertTrue(productService.deleteProduct(any()));
        verify(productRepositories).deleteById(any());
    }

    @Test
    void checkUpdateProductShouldDoesNotThrow() {

        final var productUpdate = aProduct().withId(123L).build();

        doReturn(Optional.of(productUpdate)).when(productRepositories).findById(any());

        assertDoesNotThrow(() -> productService.updateProduct(any(), productUpdate));
        verify(productRepositories).findById(any());
    }
}
