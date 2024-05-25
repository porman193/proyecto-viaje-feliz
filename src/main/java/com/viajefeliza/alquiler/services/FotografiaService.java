package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Fotografia;
import com.viajefeliza.alquiler.repositories.FotografiaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotografiaService {

    @Autowired
    private FotografiaRepo fotografiaRepo;

    public Fotografia getFotografiaById(Integer id) {
        return fotografiaRepo.findById(id).orElse(null);
    }
}
