package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

}
