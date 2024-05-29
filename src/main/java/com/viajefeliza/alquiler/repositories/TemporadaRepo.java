package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaRepo extends JpaRepository<Temporada, Integer> {
        Temporada findByTemporada(String temporada);
}
