package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepo extends JpaRepository<Pagos, Integer> {

}
