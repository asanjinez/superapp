package com.superapp.product_service.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IProductJpaDao extends JpaRepository<Product,Integer> {
    public Optional<Product> findByName(String name);
}
