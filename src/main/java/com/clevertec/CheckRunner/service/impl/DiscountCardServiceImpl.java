package com.clevertec.CheckRunner.service.impl;

import com.clevertec.CheckRunner.exeption.DiscountCardNotFoundException;
import com.clevertec.CheckRunner.model.DiscountCard;
import com.clevertec.CheckRunner.repositorie.DiscountCardRepository;
import com.clevertec.CheckRunner.service.DiscountCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Component
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public DiscountCard findByNumberCard(Integer numberCard) {
        return discountCardRepository.findByNumberCard(numberCard);
    }

    public List<DiscountCard> allDiscountCard() {
        return discountCardRepository.findAll();
    }

    public Optional<DiscountCard> findById(Long id) {
        return discountCardRepository.findById(id);
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
        }).orElseThrow(() -> new DiscountCardNotFoundException("Discount card non found"));
        return true;
    }
}
