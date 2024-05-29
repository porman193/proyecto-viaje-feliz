package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.repositories.ReservaRepo;
import com.viajefeliza.alquiler.repositories.TemporadaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepo reservaRepo;

    @Autowired
    private TemporadaService temporadaService;

    @Autowired
    private TemporadaRepo temporadaRepo;


    public void saveReserva(Reserva reserva) {
        reservaRepo.save(reserva);
    }
    public float calculatePrice(Property property, Date arrivalDate, Date departureDate) {
        float finalPrice = 0;
        float highSeasonPrice = temporadaRepo.findByTemporada("Alta").getPorcentajeAumento();
        float lowSeasonPrice = temporadaRepo.findByTemporada("Baja").getPorcentajeAumento();

        int days = (int) ((departureDate.getTime() - arrivalDate.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println("Dias de reserva: " + days);
        int highSeasonDays = temporadaService.daysInHighSeason(arrivalDate, departureDate);
        int lowSeasonDays = 0;
        if(highSeasonDays > days) {
            highSeasonDays = days;
        }else {
            lowSeasonDays = days - highSeasonDays;
        }

        System.out.println("Dias en temporada alta: " + highSeasonDays);
        System.out.println("Dias en temporada baja: " + lowSeasonDays);

        finalPrice += highSeasonDays * highSeasonPrice * property.getPrecioBase();
        System.out.println("Precio en temporada alta: " + finalPrice);
        finalPrice += lowSeasonDays * lowSeasonPrice * property.getPrecioBase();

        return finalPrice;
    }
}
