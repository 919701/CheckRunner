package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.repositories.DiscountCardRepository;
import com.clevertec.CheckRunner.services.DiscountCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public DiscountCard findByNumberCard(Integer numberCard) {
        return discountCardRepository.findByNumberCard(numberCard);
    }

    public List<DiscountCard> allDiscountCard() {
        return discountCardRepository.findAll();
    }

    public DiscountCard findById(Long id) {
        return discountCardRepository.findById(id).orElse(null);
    }

    @Transactional
    public Boolean saveDiscountCard(DiscountCard discountCard) {
        discountCardRepository.save(discountCard);
        return true;
    }

    @Transactional
    public Boolean deleteDiscountCardByNumber(Integer numberCard) {
        discountCardRepository.deleteByNumberCard(numberCard);
        return true;
    }

    @Transactional
    public Boolean updateDiscountCard(Long id, DiscountCard discountCard) {
        discountCardRepository.findById(id).map(update -> {
            update.setNumberCard(discountCard.getNumberCard());
            update.setDiscountPercent(discountCard.getDiscountPercent());
            return update;
        }).orElseThrow();
        return true;
    }

    @Override
    public Boolean isDiscountCard(DiscountCard discountCard) {
        if (discountCardRepository.findByNumberCard(discountCard.getNumberCard()) != null) {
            return true;
        }
        return false;
    }
}
