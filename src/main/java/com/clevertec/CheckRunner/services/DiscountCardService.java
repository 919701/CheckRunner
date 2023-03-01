package com.clevertec.CheckRunner.services;

import com.clevertec.CheckRunner.models.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {

     DiscountCard findByNumberCard(Integer numberCard);

    List<DiscountCard> allDiscountCard();

    Optional<DiscountCard> findById(Long id);

    Boolean saveDiscountCard(DiscountCard discountCard);

    Boolean deleteDiscountCardByNumber(Integer numberCard);

    Boolean updateDiscountCard(Long id, DiscountCard discountCard);

}
