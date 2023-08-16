package com.superapp.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartJpaDao extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM cart WHERE person_id = ?idPerson")
    Optional<Cart> findByPerson(Integer idPerson);
}
