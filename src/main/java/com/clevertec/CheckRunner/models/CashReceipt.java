package com.clevertec.CheckRunner.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CashReceipt {

    private Long id;
    @Builder.Default
    private Map<Product, Double> products = new HashMap<>();
    private DiscountCard discountCard;
    private LocalDateTime dateTime;

}
