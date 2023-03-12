package com.clevertec.CheckRunner.repositories;

import com.clevertec.CheckRunner.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepositories extends JpaRepository<Product,Long> {

}
