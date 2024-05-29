package com.viajefeliza.alquiler.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class DestinationsController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/destinations")
    public String destinations(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "destinations/destinations";
    }

    @GetMapping("/property/{id}")
    public String propertyDetail(@PathVariable Integer id,@RequestParam(required = false)String error,Model model) {
        Property property = propertyService.getPropertyById(id);
        if(error != null){
            model.addAttribute("error", error);
        }
        model.addAttribute("property", property);
        return "destinations/propertyDetail";
    }


    @PostMapping("/search")
    public String searchProperties(
            @RequestParam(required = false) String lugar,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date llegada,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date salida,
            @RequestParam(required = false) Integer personas,
            Model model) {

        List<Property> properties = propertyService.searchProperties(lugar, llegada, salida, personas);
        System.out.println(properties);
        model.addAttribute("properties", properties);
        return "destinations/destinations";
    }
}
