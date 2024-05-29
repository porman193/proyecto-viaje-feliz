package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;
import com.viajefeliza.alquiler.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ReservaController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/reservar")
    public String reservar(
            @RequestParam Integer propertyId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date llegada,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date salida,
            @RequestParam Integer personas)
    {
        // buscar la propiedad por su ID
        Property property = propertyService.getPropertyById(propertyId);
        if(property == null) {
            return "error";
        }
        if(llegada.after(salida)) {
            return "redirect:/property/" + propertyId + "?error=fecha";
        }
        if(personas> property.getCapacidad()) {
            return "redirect:/property/" + propertyId + "?error=capacidad";
        }
        if(propertyService.isPropertyReserved(property, llegada, salida)) {
            return "redirect:/property/" + propertyId + "?error=reservado";
        }
        float precio = reservaService.calculatePrice(property, llegada, salida);
        System.out.println("Precio de la reserva: " + precio);
        return "index";
    }

    @GetMapping("/calcular-precio-total")
    public ResponseEntity<Float> calcularPrecioTotal(
            @RequestParam Integer propertyId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date llegada,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date salida
    ) {
        // buscar la propiedad por su ID
        Property property = propertyService.getPropertyById(propertyId);
        float precio = reservaService.calculatePrice(property, llegada, salida);
        System.out.println("Precio de la reserva: " + precio);

        // Devuelve el precio como respuesta
        return ResponseEntity.ok(precio);
    }
}
