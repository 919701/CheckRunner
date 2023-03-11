package com.clevertec.CheckRunner.repositorie;

import com.clevertec.CheckRunner.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepositories extends JpaRepository<Product,Long> {

}
