package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Pagos;
import com.viajefeliza.alquiler.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagosRepo extends JpaRepository<Pagos, Integer> {
    List<Pagos> findPagosByReserva(Reserva reserva);
}
