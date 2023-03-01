package com.clevertec.CheckRunner.utils.impl;

import com.clevertec.CheckRunner.models.Product;
import com.clevertec.CheckRunner.utils.TestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aProduct")
@With
public class ProductTestBuilder implements TestBuilder<Product> {
    private Long id = 1L;
    private String title = "Product";
    private Double price = 10.0;
    private Boolean discount = false;


    @Override
    public Product build() {
        final var product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDiscount(discount);
        return product;
    }
}
