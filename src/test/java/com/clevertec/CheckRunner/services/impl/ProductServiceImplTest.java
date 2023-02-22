package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.repositories.ProductRepositories;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.lang.System.out;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @MockBean
    private ProductRepositories productRepositories;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    void findAllProducts() {
//        when(repositories.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
//        assertThrows(ResponseStatusException.class, service::findAllProducts);
//        verify(repositories).findAll();
    }

    @Test
    void findById() {
//        when(repositories.findById(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
//        assertThrows(ResponseStatusException.class, () -> service.findById(1L));
//        verify(repositories).findById(any());
//        Optional<Product> productTest = Optional.of(new Product(1L, "productTest1", 10d, false));
//        when(repositories.findById(1L)).thenReturn(productTest);
//        Product product = service.findById(1L);
//        verify(repositories).findById(1L);
    }

    @Test
    void saveProduct() {

        Product product = new Product(1L, "product1", 10d, false);
        when(productRepositories.findById(any())).thenReturn(Optional.of(product));
        Optional<Product> productTest = productService.findById(1L);
        out.println("-".repeat(60) + productTest);
    }

    @Test
    void deleteProduct() {
//        doNothing().when(repositories).deleteById(any());
//        service.deleteProduct(1L);
//        verify(repositories).deleteById(any());
    }

    @Test
    void updateProduct() {
//        Product product = Product.builder()
//                .id(1L)
//                .title("productTest")
//                .price(10d)
//                .discount(true)
//                .build();
//        when(repositories.findById(any())).thenReturn(Optional.ofNullable(product));
//        assertDoesNotThrow(() -> service.updateProduct(1L, product));
//        verify(repositories).findById(any());
    }
}