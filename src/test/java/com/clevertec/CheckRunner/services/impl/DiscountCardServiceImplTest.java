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
    void checkFindByNumberCardShouldReturn123() {
        when(cardRepository.findByNumberCard(123)).thenReturn(discountCards.get(0));
        DiscountCard card = new DiscountCard(1L, 123, 5.0);
        DiscountCard cardTest = cardService.findByNumberCard(123);
        assertEquals(card, cardTest);
        verify(cardRepository).findByNumberCard(anyInt());
    }

    @Test
    void checkAllDiscountCardShouldReturn3() {
        when(cardRepository.findAll()).thenReturn(discountCards);
        long expectedSize = 3;
        List<DiscountCard> cardsTest = cardService.allDiscountCard();
        assertEquals(expectedSize, cardsTest.size());
        verify(cardRepository).findAll();
    }

    @Test
    void checkFindByIdShouldReturn1() {
        when(cardRepository.findById(1L)).thenReturn(Optional.ofNullable(discountCards.get(0)));
        Optional<DiscountCard> expectedCard = Optional.of(new DiscountCard(1L, 123, 5.0));
        Optional<DiscountCard> cardTest = cardService.findById(1L);
        assertEquals(expectedCard, cardTest);
        verify(cardRepository).findById(any());
    }

    @Test
    void checkSaveDiscountCard() {
        assertTrue(cardService.saveDiscountCard(any()));
        verify(cardRepository).save(any());
    }

    @Test
    void checkDeleteDiscountCardByNumberShouldTrue() {
        doNothing().when(cardRepository).deleteByNumberCard(any());
        assertTrue(cardService.deleteDiscountCardByNumber(any()));
        verify(cardRepository).deleteByNumberCard(any());
    }

    @Test
    void checkUpdateDiscountCardShouldDoesNotThrow() {
        DiscountCard discountCard = new DiscountCard(123L, 123456, 12.0);
        when(cardRepository.findById(any())).thenReturn(Optional.of(discountCard));
        assertDoesNotThrow(()-> cardService.updateDiscountCard(123L,discountCard));
        verify(cardRepository).findById(any());
    }
}