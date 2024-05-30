package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.Pagos;
import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.model.User;
import com.viajefeliza.alquiler.services.PagosServices;
import com.viajefeliza.alquiler.services.PropertyService;
import com.viajefeliza.alquiler.services.ReservaService;
import com.viajefeliza.alquiler.services.TemporadaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@Controller
public class ReservaController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private PagosServices pagosServices;

    @Autowired
    TemporadaService temporadaService;

    @PostMapping("/reservar")
    public String reservar(
            @RequestParam Integer propertyId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date llegada,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date salida,
            @RequestParam Integer personas,
            HttpSession session, Model model
            )
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); // Restar un día
        Date fechaAnterior = cal.getTime();
        // buscar la propiedad por su ID
        Property property = propertyService.getPropertyById(propertyId);
        if(property == null) {
            return "error";
        }
        if(session.getAttribute("userAuth") == null) {
            return "redirect:/property/" + propertyId + "?error=auth";
        }
        if(llegada.after(salida) || llegada.equals(salida) || llegada.before(fechaAnterior) ){
            return "redirect:/property/" + propertyId + "?error=fecha";
        }
        if(personas> property.getCapacidad()) {
            return "redirect:/property/" + propertyId + "?error=capacidad";
        }
        if(propertyService.isPropertyReserved(property, llegada, salida)) {
            return "redirect:/property/" + propertyId + "?error=reservado";
        }

        float precio = reservaService.calculatePrice(property, llegada, salida);
        int days = (int) ((salida.getTime() - llegada.getTime()) / (1000 * 60 * 60 * 24));
        int daysHighSeason = temporadaService.daysInHighSeason(llegada, salida);
        int daysLowSeason = days - daysHighSeason;
        if(daysHighSeason > daysLowSeason){
            model.addAttribute("temporada", "Alta");
        }else {
            model.addAttribute("temporada", "Baja");
        }
        System.out.println("Precio de la reserva: " + precio);
        model.addAttribute("precio", precio);
        model.addAttribute("id_property", propertyId);
        model.addAttribute("llegada", llegada);
        model.addAttribute("salida", salida);
        model.addAttribute("personas", personas);
        return "pagos/pasarela";
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

    @PostMapping("/realizar-pago")
    public String realizarPago(
            @RequestParam Integer propertyId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date llegada,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date salida,
            @RequestParam Integer personas,
            @RequestParam Float precio,
            @RequestParam String tipo_pago,
            @RequestParam (required = false)Float monto,
            @RequestParam String temporada,
            HttpSession session
    )
    {
        if(tipo_pago.equals("total")) {
            Reserva reserva = new Reserva();
            reserva.setFechaIni(llegada);
            reserva.setFechaFin(salida);
            reserva.setNumPersonas(personas);
            reserva.setPrecioTotal(precio);
            Property property = propertyService.getPropertyById(propertyId);
            reserva.setProperty(property);
            reserva.setMascotas(property.getAceptaMascotas());
            reserva.setUsuario((User) session.getAttribute("userAuth"));
            reserva.setTemporada(temporadaService.findByTemporada(temporada));
            reservaService.saveReserva(reserva);
            Pagos pago = new Pagos();
            pago.setMonto(precio);
            pago.setFechaPago(new Date());
            pago.setMetodoPago("total");
            pago.setReserva(reserva);
            pagosServices.savePagos(pago);
            return "redirect:/";
        }
        return "redirect:/detinations/destinations";
    }



}
