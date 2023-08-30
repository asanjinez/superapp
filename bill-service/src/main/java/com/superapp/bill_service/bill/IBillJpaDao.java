package com.superapp.bill_service.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillJpaDao extends JpaRepository<Bill, Integer>  {
}
