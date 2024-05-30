package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Pagos;
import com.viajefeliza.alquiler.repositories.PagosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagosServices {
    @Autowired
    private PagosRepo pagosRepo;

    public void savePagos(Pagos pagos) {
        pagosRepo.save(pagos);
    }
}
