package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {
    List<Reserva> findReservasByUsuario(User user);
}
