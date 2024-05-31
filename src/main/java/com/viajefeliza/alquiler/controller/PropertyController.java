
package com.viajefeliza.alquiler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;

@Controller
@RequestMapping("/propiedades")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("")
    public String showPropertyForm(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "propiedades/propiedades";
    }
}
