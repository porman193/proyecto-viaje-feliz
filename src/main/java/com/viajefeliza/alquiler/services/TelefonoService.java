package com.viajefeliza.alquiler.services;


import com.viajefeliza.alquiler.model.TelefonosUsuario;
import com.viajefeliza.alquiler.model.User;
import com.viajefeliza.alquiler.repositories.TelefonosUsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefonoService {
    @Autowired
    private TelefonosUsuariosRepo telefonosUsuariosRepo;

    public List<TelefonosUsuario> getTelefonosByUsuario(User user) {
        return telefonosUsuariosRepo.findByUsuario(user);
    }

}
