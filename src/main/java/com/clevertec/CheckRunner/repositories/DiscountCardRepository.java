package com.clevertec.CheckRunner.repositories;

import com.clevertec.CheckRunner.models.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCardRepository extends JpaRepository<DiscountCard,Long> {

    DiscountCard findByNumberCard(Integer numberCard);


    void deleteByNumberCard(Integer numberCard);
}
