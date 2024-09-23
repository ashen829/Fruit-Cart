package com.fruitcart.FruitCart.repository;

import com.fruitcart.FruitCart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByName(String name);
}
