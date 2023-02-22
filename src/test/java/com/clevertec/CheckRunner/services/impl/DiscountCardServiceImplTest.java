package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.repositories.DiscountCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {
    @Mock
    private DiscountCardRepository repository;
    @InjectMocks
    private DiscountCardServiceImpl service;
    List<DiscountCard> discountCards = List.of(
            new DiscountCard(1L, 123, 5.0),
            new DiscountCard(2L, 234, 10.0),
            new DiscountCard(3L, 345, 15.0)
    );

    @Test
    void findByNumberCard() {
        when(repository.findByNumberCard(123)).thenReturn(discountCards.get(0));
        DiscountCard cardTest = service.findByNumberCard(123);
        assertSame(discountCards.get(0), cardTest);
        verify(repository).findByNumberCard(anyInt());
    }

    @Test
    void allDiscountCard() {
        when(repository.findAll()).thenReturn(discountCards);
        List<DiscountCard> cardsTest = service.allDiscountCard();
        assertSame(discountCards, cardsTest);
        verify(repository).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(discountCards.get(0)));
        DiscountCard cardTest = service.findById(1L);
        assertSame(discountCards.get(0), cardTest);
        verify(repository).findById(any());
    }

    @Test
    void saveDiscountCard() {
        assertTrue(service.saveDiscountCard(any()));
        verify(repository).save(any());
    }

    @Test
    void deleteDiscountCardByNumber() {
        assertTrue(service.deleteDiscountCardByNumber(any()));
        verify(repository).deleteByNumberCard(any());
    }

    @Test
    void updateDiscountCard() {
        DiscountCard discountCard = new DiscountCard(123L, 123456, 12.0);
        when(repository.findById(any())).thenReturn(Optional.of(discountCard));
        assertDoesNotThrow(()->service.updateDiscountCard(123L,discountCard));
        verify(repository).findById(any());
    }
}