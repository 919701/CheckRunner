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
    private DiscountCardRepository cardRepository;
    @InjectMocks
    private DiscountCardServiceImpl cardService;
    List<DiscountCard> discountCards = List.of(
            new DiscountCard(1L, 123, 5.0),
            new DiscountCard(2L, 234, 10.0),
            new DiscountCard(3L, 345, 15.0)
    );

    @Test
    void findByNumberCard() {
        doReturn(discountCards.get(0)).when(cardRepository).findByNumberCard(123);
        DiscountCard cardTest = cardService.findByNumberCard(123);
        assertSame(discountCards.get(0), cardTest);
        verify(cardRepository).findByNumberCard(anyInt());
    }

    @Test
    void allDiscountCard() {
        doReturn(discountCards).when(cardRepository).findAll();
        List<DiscountCard> cardsTest = cardService.allDiscountCard();
        assertSame(discountCards, cardsTest);
        verify(cardRepository).findAll();
    }

    @Test
    void findById() {
        doReturn(Optional.ofNullable(discountCards.get(0))).when(cardRepository).findById(1L);
        Optional<DiscountCard> cardTest = cardService.findById(1L);
        assertEquals(Optional.ofNullable(discountCards.get(0)), cardTest);
        verify(cardRepository).findById(any());
    }

    @Test
    void saveDiscountCard() {
        assertTrue(cardService.saveDiscountCard(any()));
        verify(cardRepository).save(any());
    }

    @Test
    void deleteDiscountCardByNumber() {
        doNothing().when(cardRepository).deleteByNumberCard(any());
        assertTrue(cardService.deleteDiscountCardByNumber(any()));
        verify(cardRepository).deleteByNumberCard(any());
    }

    @Test
    void updateDiscountCard() {
        DiscountCard discountCard = new DiscountCard(123L, 123456, 12.0);
        doReturn(Optional.of(discountCard)).when(cardRepository);
        assertDoesNotThrow(()-> cardService.updateDiscountCard(123L,discountCard));
        verify(cardRepository).findById(any());
    }
}