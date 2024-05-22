package com.ross.gamis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ross.gamis.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
