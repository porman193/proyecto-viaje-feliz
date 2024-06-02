package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Pagos;
import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.repositories.PagosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagosServices {
    @Autowired
    private PagosRepo pagosRepo;

    public void savePagos(Pagos pagos) {
        pagosRepo.save(pagos);
    }

    public List<Pagos> getPagosByReserva(Reserva reserva) {
        return pagosRepo.findPagosByReserva(reserva);
    }
}
