package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.repositories.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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

    public float calculatePrice(Property property, LocalDate arrivalDate, LocalDate departureDate) {
        float basePrice = property.getPrecioBase();
        float finalPrice = basePrice;

        boolean isHighSeason = isHighSeason(arrivalDate, departureDate);
        if (isHighSeason) {
            finalPrice *= 1.30;
        } else {
            finalPrice *= 1.10;
        }

        return finalPrice;
    }

    private boolean isHighSeason(LocalDate arrivalDate, LocalDate departureDate) {
        // Check if the dates fall within the high season periods
        LocalDate highSeasonStart1 = LocalDate.of(arrivalDate.getYear(), Month.DECEMBER, 15);
        LocalDate highSeasonEnd1 = LocalDate.of(arrivalDate.getYear() + 1, Month.JANUARY, 15);
        LocalDate highSeasonStart2 = LocalDate.of(arrivalDate.getYear(), Month.JUNE, 15);
        LocalDate highSeasonEnd2 = LocalDate.of(arrivalDate.getYear(), Month.JULY, 15);

        // Easter week and public holidays are omitted for simplicity

        return (arrivalDate.isAfter(highSeasonStart1) && departureDate.isBefore(highSeasonEnd1))
                || (arrivalDate.isAfter(highSeasonStart2) && departureDate.isBefore(highSeasonEnd2));
    }
}
