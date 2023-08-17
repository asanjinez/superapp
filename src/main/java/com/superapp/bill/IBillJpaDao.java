package com.superapp.bill;

import com.superapp.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBillJpaDao extends JpaRepository<Bill, Integer>  {
    Optional<Bill> findByPerson(Integer id);
}
