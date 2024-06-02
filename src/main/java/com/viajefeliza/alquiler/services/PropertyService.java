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

    public List<Property> getPropertiesByReservationDate(Date arrivalDate, Date departureDate) {
        return propertyRepo.findPropertiesByReservationDate(arrivalDate, departureDate);
    }
    public List<Property> searchProperties(String lugar, Date llegada, Date salida, Integer personas) {
        // Crear instancias de listas para almacenar los resultados
        List<Property> propertiesByCapacity = new ArrayList<>();
        List<Property> propertiesByDate = new ArrayList<>();
        List<Property> propertiesByPlace = new ArrayList<>();

        // Realizar las consultas y almacenar los resultados en las listas correspondientes
        if (personas != null) {
            propertiesByCapacity = propertyRepo.findPropertiesByCapacity(personas);
            System.out.println("Capacidad propiedades:"+propertiesByCapacity);
        }
        if (llegada != null && salida != null) {
            propertiesByDate = propertyRepo.findPropertiesByReservationDate(llegada, salida);
            System.out.println("Fecha propiedades:"+propertiesByDate);
        }
        if (lugar != null && !lugar.isEmpty()) {
            propertiesByPlace = propertyRepo.findPropertiesByPlace(lugar);
            System.out.println("Lugar propiedades:"+propertiesByPlace);
        }

        // Combinar las listas y eliminar duplicados utilizando un Set
        Set<Property> SetCapacity = new HashSet<>(propertiesByCapacity);
        Set<Property> SetDate = new HashSet<>(propertiesByDate);
        Set<Property> SetPlace = new HashSet<>(propertiesByPlace);


        if (SetCapacity.isEmpty() && SetDate.isEmpty()){
            return new ArrayList<>(SetPlace);
        }else if (SetCapacity.isEmpty() && SetPlace.isEmpty()){
            List<Property> properties = new ArrayList<>();
            properties = propertyRepo.findAll();
            if(SetDate.isEmpty()) return properties;
            Set<Property> SetProperties = new HashSet<>(properties);
            SetProperties.removeAll(SetDate);
            return new ArrayList<>(SetProperties);
        } else if (SetDate.isEmpty() && SetPlace.isEmpty()) {
            return new ArrayList<>(SetCapacity);
        }else if (SetDate.isEmpty()){
            SetCapacity.retainAll(SetPlace);
            return new ArrayList<>(SetCapacity);
        } else if (SetCapacity.isEmpty()){
            SetPlace.removeAll(SetDate);
            return new ArrayList<>(SetPlace);
        } else if (SetPlace.isEmpty()){
            SetCapacity.removeAll(SetDate);
            return new ArrayList<>(SetDate);
        }

        return new ArrayList<>();
    }

    public Boolean isPropertyReserved(Property property, Date arrivalDate, Date departureDate) {
        List<Property> properties = propertyRepo.findPropertiesByReservationDate(arrivalDate, departureDate);
        return properties.contains(property);
    }

}
