package com.superapp.validator_service.validator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IValidatorJpaDao extends JpaRepository<Validator, Integer> {
    Optional<Validator> findByCodeProduct(String code_product);
    List<Validator> findByCodeProductIn(List<String> codes);
}
