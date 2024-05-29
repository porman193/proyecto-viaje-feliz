package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.repositories.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    public Property getPropertyById(Integer id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    public List<Property> searchProperties(String lugar, Date llegada, Date salida, Integer personas) {
        // Crear instancias de listas para almacenar los resultados
        List<Property> propertiesByCapacity = new ArrayList<>();
        List<Property> propertiesByDate = new ArrayList<>();
        List<Property> propertiesByPlace = new ArrayList<>();

        // Realizar las consultas y almacenar los resultados en las listas correspondientes
        if (personas != null) {
            propertiesByCapacity = propertyRepo.findPropertiesByCapacity(personas);
        }
        if (llegada != null && salida != null) {
            propertiesByDate = propertyRepo.findPropertiesByDate(llegada, salida);
        }
        if (lugar != null && !lugar.isEmpty()) {
            propertiesByPlace = propertyRepo.findPropertiesByPlace(lugar);
        }

        // Combinar las listas y eliminar duplicados utilizando un Set
        Set<Property> combinedProperties = new HashSet<>();
        combinedProperties.addAll(propertiesByCapacity);
        combinedProperties.addAll(propertiesByDate);
        combinedProperties.addAll(propertiesByPlace);

        // Convertir el Set de vuelta a una List y devolverla
        return new ArrayList<>(combinedProperties);
    }

    public Boolean isPropertyReserved(Property property, Date arrivalDate, Date departureDate) {
        List<Property> properties = propertyRepo.findPropertiesByDate(arrivalDate, departureDate);
        return !properties.contains(property);
    }

}
