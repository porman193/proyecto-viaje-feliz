package com.viajefeliza.alquiler.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showEditReservaForm(@PathVariable("id") Integer id, Model model) {
        Reserva reserva = reservaService.getReservaById(id);
        model.addAttribute("reserva", reserva);
        return "reservas/editar_reserva";
    }

    @PostMapping("/editar-reservas/{id_reserva}")
public String updateReserva(@PathVariable("id_reserva") Integer idReserva, @RequestParam String comentariosEncuesta, @RequestParam Integer califEncuesta, @RequestParam String estado, @RequestParam Date fechaIni, @RequestParam Date fechaFin, @RequestParam Float precioTotal, @RequestParam(required = false) Boolean mascotas, @RequestParam Integer numPersonas, Model model) {
    Reserva reserva = reservaService.getReservaById(idReserva);
    reserva.setComentariosEncuesta(comentariosEncuesta);
    reserva.setCalifEncuesta(califEncuesta);
    reserva.setEstado(estado);
    reserva.setFechaIni(fechaIni);
    reserva.setFechaFin(fechaFin);
    reserva.setPrecioTotal(precioTotal);
    reserva.setMascotas(mascotas);
    reserva.setNumPersonas(numPersonas);
    
    reservaService.updateReserva(reserva);
    return "redirect:/reservas";
}

}
