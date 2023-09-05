package com.superapp.auth_service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserJpaDao extends JpaRepository<User,Integer> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
