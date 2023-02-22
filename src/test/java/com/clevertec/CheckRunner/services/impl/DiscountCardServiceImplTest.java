package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.repositories.DiscountCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@SpringBootTest
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @Mock
    DiscountCardRepository repository;

    @InjectMocks
    DiscountCardServiceImpl service;
    List<DiscountCard> cards = List.of(
            new DiscountCard(1L, 123, 10d),
            new DiscountCard(2L, 234, 20d),
            new DiscountCard(3L, 345, 30d)
    );

    @Test
    void findByNumberCard() {
    }

    @Test
    void allDiscountCard() {

    }

    @Test
    void findById() {
//        when(repository.findById(1L)).thenReturn(Optional.ofNullable(cards.get(0)));
//        assertSame(0, service.findById(1L));
    }

    @Test
    void saveDiscountCard() {
//        when(repository.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
//        DiscountCard discountCardTest = DiscountCard.builder()
//                .id(1L)
//                .numberCard(123)
//                .discountPercent(10d)
//                .build();
//        assertThrows(ResponseStatusException.class, () -> service.saveDiscountCard(discountCardTest));
//        verify(repository).save(any());

    }

    @Test
    void deleteDiscountCardByNumber() {
        doNothing().when(repository).deleteByNumberCard(any());
        service.deleteDiscountCardByNumber(123);
        verify(repository).deleteByNumberCard(anyInt());
    }

    @Test
    void updateDiscountCard() {
    }
}