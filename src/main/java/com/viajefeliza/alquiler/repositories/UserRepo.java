package com.viajefeliza.alquiler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viajefeliza.alquiler.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
