package com.viajefeliza.alquiler.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String propertyDetail(@PathVariable Integer id,Model model) {
        Property property = propertyService.getPropertyById(id);


        model.addAttribute("property", property);
        return "destinations/propertyDetail";
    }
}
