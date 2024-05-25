package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.repositories.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }
}
