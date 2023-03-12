package com.clevertec.CheckRunner.repositorie;

import com.clevertec.CheckRunner.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCardRepository extends JpaRepository<DiscountCard,Long> {

    DiscountCard findByNumberCard(Integer numberCard);


    void deleteByNumberCard(Integer numberCard);
}
