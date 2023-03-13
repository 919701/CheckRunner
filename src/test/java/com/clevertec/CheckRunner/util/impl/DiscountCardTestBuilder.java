package com.clevertec.CheckRunner.util.impl;

import com.clevertec.CheckRunner.model.DiscountCard;
import com.clevertec.CheckRunner.util.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aDiscountCard")
@With
public class DiscountCardTestBuilder implements TestBuilder<DiscountCard> {

    private Long id = 1L;
    private Integer numberCard = 123;
    private Double discountPercent = 10.0;

    @Override
    public DiscountCard build() {
        final var discountCard = new DiscountCard();
        discountCard.setId(id);
        discountCard.setNumberCard(numberCard);
        discountCard.setDiscountPercent(discountPercent);
        return discountCard;
    }
}
