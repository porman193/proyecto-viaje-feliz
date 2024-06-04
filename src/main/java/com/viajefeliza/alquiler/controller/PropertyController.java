package com.viajefeliza.alquiler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.PropertyService;

@Controller

public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/propiedades")
    public String showPropertyForm(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "propiedades/propiedades";
    }

    @GetMapping("/editar/{id}")
    public String editPropertyForm(@PathVariable Integer id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "propiedades/editar_propiedad"; // Nombre de la vista HTML para editar propiedad
    }
    
    @PostMapping("/editar/{idPropiedad}")
    public String updateProperty(@PathVariable Integer idPropiedad, @RequestParam Integer numHabitaciones, @RequestParam Float precioBase, @RequestParam(required = false) Boolean aceptaMascotas, @RequestParam Integer numBanos, @RequestParam(required = false) Boolean calefaccion, @RequestParam(required = false) Boolean aireAcondicionado, @RequestParam String ubicacionDireccion, @RequestParam Integer capacidad, @RequestParam String tipoPropiedad, @RequestParam String paisUbicacion, @RequestParam String ciudadUbicacion, @RequestParam String regionUbicacion) {
        Property property = propertyService.getPropertyById(idPropiedad);
        property.setNumHabitaciones(numHabitaciones);
        property.setPrecioBase(precioBase);
        property.setAceptaMascotas(aceptaMascotas != null && aceptaMascotas);
        property.setNumBanos(numBanos);
        property.setCalefaccion(calefaccion != null && calefaccion);
        property.setAireAcondicionado(aireAcondicionado != null && aireAcondicionado);
        property.setUbicacionDireccion(ubicacionDireccion);
        property.setCapacidad(capacidad);
        property.getTipoPropiedad().setTipo(tipoPropiedad);
        property.getPaisUbicacion().setPais(paisUbicacion);
        property.getCiudadUbicacion().setCiudad(ciudadUbicacion);
        property.getRegionUbicacion().setRegion(regionUbicacion);
        
        propertyService.updateProperty(property);
        return "redirect:/propiedades"; // Redirige a la lista de propiedades después de la actualización
    }
    
      
    
}
