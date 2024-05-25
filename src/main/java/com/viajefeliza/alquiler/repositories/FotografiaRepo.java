package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotografiaRepo extends JpaRepository<Fotografia, Integer> {
    // Puedes agregar métodos de consulta personalizados si es necesario
}
