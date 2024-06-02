package com.viajefeliza.alquiler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.services.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaAdminController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("")
    public String showReservaForm(Model model) {
        List<Reserva> reservas = reservaService.getAllReservas();
        model.addAttribute("reservas", reservas);
        return "reservas/reservas";
    }
}
