package com.viajefeliza.alquiler.repositories;

import com.viajefeliza.alquiler.model.TelefonosUsuario;
import com.viajefeliza.alquiler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  TelefonosUsuariosRepo extends JpaRepository<TelefonosUsuario, Integer> {
    List<TelefonosUsuario> findByUsuario(User user);

}
