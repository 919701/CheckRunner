package com.clevertec.CheckRunner.service.impl;

import com.clevertec.CheckRunner.model.DiscountCard;
import com.clevertec.CheckRunner.repositorie.DiscountCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.clevertec.CheckRunner.util.impl.DiscountCardTestBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {
    @Mock
    private DiscountCardRepository cardRepository;
    @InjectMocks
    private DiscountCardServiceImpl cardService;

    final List<DiscountCard> discountCards = List.of(
            aDiscountCard().build(),
            aDiscountCard()
                    .withId(2L)
                    .build()
    );

    @Test
    void findByNumberCard() {

        doReturn(discountCards.get(0)).when(cardRepository).findByNumberCard(123);

        final var cardTest = cardService.findByNumberCard(123);

        assertSame(discountCards.get(0), cardTest);
        verify(cardRepository).findByNumberCard(anyInt());

    }

    @Test
    void allDiscountCard() {

        doReturn(discountCards).when(cardRepository).findAll();

        final var cardsTest = cardService.allDiscountCard();

        assertSame(discountCards, cardsTest);
        verify(cardRepository).findAll();
    }

    @Test
    void findById() {

        doReturn(Optional.ofNullable(discountCards.get(0))).when(cardRepository).findById(1L);

        final var cardTest = cardService.findById(1L);

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

        final var discountCard = aDiscountCard()
                .withId(123L)
                .build();

        doReturn(Optional.of(discountCard)).when(cardRepository).findById(123L);

        assertDoesNotThrow(() -> cardService.updateDiscountCard(123L, discountCard));
        verify(cardRepository).findById(any());
    }
}
