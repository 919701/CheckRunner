package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@AllArgsConstructor
@ExtendWith(MockitoExtension.class)
class BasketServiceImplTest {

    @Mock
    private ProductServiceImpl productService;
    @Mock
    private DiscountCardServiceImpl cardService;
    @InjectMocks
    private BasketServiceImpl basketService;

    @BeforeEach
    void setResources() {
        Product product = new Product(1L, "productName", 10.0, false);
        DiscountCard card = new DiscountCard(1L, 1234, 10.0);

        when(productService.findById(anyLong())).thenReturn(Optional.of(product));
        when(cardService.findByNumberCard(anyInt())).thenReturn(card);
    }

    @Test
    void buildBasket() {
        String[] args = {"1-2", "card-1234"};
        Basket basket = basketService.buildBasket(args);

        assertEquals(1, basket.getProducts().size());
        verify(productService).findById(anyLong());

        assertEquals(10, basket.getDiscountCard().getDiscountPercent());
        verify(cardService).findByNumberCard(anyInt());


    }

    @Test
    void testBuildBasket() {
        List<String> products = List.of("1-2");
        int cardNumber = 1234;

        Basket basket = basketService.buildBasket(products, cardNumber);

        assertEquals(1, basket.getProducts().size());
        verify(productService).findById(anyLong());

        assertEquals(10, basket.getDiscountCard().getDiscountPercent());
        verify(cardService).findByNumberCard(anyInt());
    }

    @Test
    void coastProducts() {
        String[] args = {"1-2", "card-123"};
        Basket basket = basketService.buildBasket(args);
        verify(productService).findById(anyLong());
    }

    @Test
    void totalWithoutDiscount() {
        String[] args = {"1-2", "card-123"};
        Basket basket = basketService.buildBasket(args);
        Double total = basketService.totalWithoutDiscount(basket);
        assertEquals(20, total);
    }

    @Test
    void totalWithDiscount() {
        String[] args = {"1-2", "card-123"};
        Basket basket = basketService.buildBasket(args);
        Double total = basketService.totalWithDiscount(basket);
        assertEquals(18, total);
    }
}