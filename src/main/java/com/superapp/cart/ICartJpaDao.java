package com.superapp.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartJpaDao extends JpaRepository<Cart, Integer> {
}
