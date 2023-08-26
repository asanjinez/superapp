package com.superapp.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductJpaDao extends JpaRepository<Product,Integer> {
    Optional<Product> findByName(String name);
}
