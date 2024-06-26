package com.viajefeliza.alquiler.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.services.ReservaService;

@Controller
public class ReservaAdminController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/reservas")
    public String showReservaForm(Model model) {
        List<Reserva> reservas = reservaService.getAllReservas();
        model.addAttribute("reservas", reservas);
        return "reservas/reservas";
    }

    @GetMapping("/editar-reservas/{id}")
    public String showEditReservaForm(@PathVariable Integer id, Model model) {
        Reserva reserva = reservaService.getReservaById(id);
        model.addAttribute("reserva", reserva);
        return "reservas/editar_reserva";
    }

    @PostMapping("/editar-reservas/{idReserva}")
    public String updateReserva(@PathVariable Integer idReserva, 
                                @RequestParam String comentarios, 
                                @RequestParam Integer calificacion, 
                                @RequestParam String estado, 
                                @RequestParam String fechaInicio, 
                                @RequestParam String fechaFin, 
                                @RequestParam Float precioTotal, 
                                @RequestParam(required = false) Boolean mascotas,
                                @RequestParam Integer numPersonas 
                              ) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicios = sdf.parse(fechaInicio);
        Date fechaFinDate = sdf.parse(fechaFin);

        Reserva reserva = reservaService.getReservaById(idReserva);
        reserva.setComentariosEncuesta(comentarios);
        reserva.setCalifEncuesta(calificacion);
        reserva.setFechaIni(fechaInicios);

        reserva.setFechaFin(fechaFinDate);
       
        reserva.setPrecioTotal(precioTotal);
        reserva.setMascotas(mascotas);
        reserva.setNumPersonas(numPersonas);

        reservaService.updateReserva(reserva);
        return "redirect:/reservas";
    }
    @GetMapping("/comentarios-reserva/{id}")
    public String mostrarComentariosReserva(@PathVariable Integer idReserva, Model model) {
        
        Reserva reserva = reservaService.getReservaById(idReserva);
        model.addAttribute("comentarios", reserva.getComentariosEncuesta());
        return "reservas/comentarios"; // Este es el nombre del archivo HTML sin la extensión
    }
}
