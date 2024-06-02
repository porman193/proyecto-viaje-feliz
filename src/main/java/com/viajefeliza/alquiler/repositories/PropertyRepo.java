package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {
    @Query("SELECT p FROM Property p WHERE p.ciudadUbicacion.ciudad = :lugar ")
    List<Property> findPropertiesByPlace(@Param("lugar") String lugar);

    @Query("SELECT p FROM Property p WHERE p.capacidad >= :personas ")
    List<Property> findPropertiesByCapacity(@Param("personas") Integer personas);

    @Query("SELECT p FROM Property p WHERE EXISTS (" +
            "SELECT r FROM Reserva r WHERE r.property.idPropiedad = p.idPropiedad " +
            "AND (" +
            "    (r.fechaIni <= :salida AND r.fechaFin >= :llegada) " +
            "))")
    List<Property> findPropertiesByReservationDate(@Param("llegada") Date llegada, @Param("salida") Date salida);
}

