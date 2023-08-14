package com.superapp.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonJpaDao extends JpaRepository<Person,Integer> {
}
