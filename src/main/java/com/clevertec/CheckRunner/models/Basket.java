package com.clevertec.CheckRunner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Basket {

    @Builder.Default
    private Map<Product, Double> products = new HashMap<>();
    private DiscountCard discountCard;
    private LocalDateTime dateTime;

}
