package com.clevertec.CheckRunner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$",message = "Product name must contain letters, numbers")
    private String title;

    @NotNull(message = "The price must be")
    @Positive(message = "Price product must be positive")
    private Double price;
    @Builder.Default
    private Boolean discount = false;

}
