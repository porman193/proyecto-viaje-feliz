package com.viajefeliza.alquiler.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DestinationsController {
    @GetMapping("/destinations")
    public String destinations() {
        return "destinations/destinations";
    }
}
