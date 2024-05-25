package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {
}
