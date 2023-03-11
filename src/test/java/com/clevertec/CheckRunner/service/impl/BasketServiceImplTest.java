package com.clevertec.CheckRunner.service.impl;

import com.clevertec.CheckRunner.model.Basket;
import com.clevertec.CheckRunner.model.DiscountCard;
import com.clevertec.CheckRunner.model.Product;
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

        doReturn(Optional.of(product)).when(productService).findById(anyLong());
        doReturn(card).when(cardService).findByNumberCard(anyInt());
    }

    @Test
    void checkBuildBasketShouldReturnBasket1() {

        String[] args = {"1-2", "card-1234"};
        Basket basket = basketService.buildBasket(args);
        final var expectedSizeBasket = 1;

        assertEquals(expectedSizeBasket, basket.getProducts().size());

        verify(productService).findById(anyLong());
    }

    @Test
    void checkBuildBasketShouldReturnPercentCard10() {

        String[] args = {"1-2", "card-1234"};
        final var basket = basketService.buildBasket(args);
        double expectedPercentCard = 10;

        assertEquals(expectedPercentCard, basket.getDiscountCard().getDiscountPercent());

        verify(cardService).findByNumberCard(anyInt());
    }

    @Test
    void checkBuildBasketShouldReturnBasket() {

        final var products = List.of("1-2");
        int cardNumber = 1234;
        long expectedSizeBasket = 1;

        Basket basket = basketService.buildBasket(products, cardNumber);

        assertEquals(expectedSizeBasket, basket.getProducts().size());
        verify(productService).findById(anyLong());
    }

    @Test
    void checkBuildBasketShouldReturnCard() {

        final var products = List.of("1-2");
        int cardNumber = 1234;
        double expectedPercentCard = 10;

        Basket basket = basketService.buildBasket(products, cardNumber);

        assertEquals(expectedPercentCard, basket.getDiscountCard().getDiscountPercent());
        verify(cardService).findByNumberCard(anyInt());
    }

    @Test
    void coastProducts() {

        String[] args = {"1-2", "card-123"};

        basketService.buildBasket(args);

        verify(productService).findById(anyLong());
    }

    @Test
    void checkTotalWithoutDiscountShouldReturnTotal20() {

        String[] args = {"1-2", "card-123"};
        double expectedTotalCoast = 20;

        final var basket = basketService.buildBasket(args);
        final var total = basketService.totalWithoutDiscount(basket);

        assertEquals(expectedTotalCoast, total);
    }

    @Test
    void checkTotalWithDiscountShouldReturnTotal18() {

        String[] args = {"1-2", "card-123"};
        double expectedTotalCoast = 18;

        final var basket = basketService.buildBasket(args);
        final var total = basketService.totalWithDiscount(basket);

        assertEquals(expectedTotalCoast, total);
    }
}
