package com.viajefeliza.alquiler.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;
import java.util.List;

@Controller
public class DestinationsController {
    @Autowired
    private PropertyService PropertyService;

    @GetMapping("/destinations")
    public String destinations(Model model) {
        List<Property> properties = PropertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "destinations/destinations";
    }
}
