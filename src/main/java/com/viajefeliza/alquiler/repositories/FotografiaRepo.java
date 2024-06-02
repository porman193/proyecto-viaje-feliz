package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Fotografia;
import com.viajefeliza.alquiler.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotografiaRepo extends JpaRepository<Fotografia, Integer> {
    // Puedes agregar métodos de consulta personalizados si es necesario
    @Query ("SELECT f FROM Fotografia f WHERE f.propiedad = :propiedad")
    List<Fotografia> findByPropertyId(Property propiedad);
}
