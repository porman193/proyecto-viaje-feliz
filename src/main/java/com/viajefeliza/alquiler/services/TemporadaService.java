package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.Temporada;
import com.viajefeliza.alquiler.repositories.TemporadaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class TemporadaService {
    @Autowired
    private TemporadaRepo temporadaRepo;

    public int daysInHighSeason(Date arrivalDate, Date departureDate) {
        Calendar arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.setTime(arrivalDate);
        Calendar departureCalendar = Calendar.getInstance();
        departureCalendar.setTime(departureDate);
        int daysInHighSeason = 0;

        // Define high season periods
        Calendar highSeasonStart1 = new GregorianCalendar(arrivalCalendar.get(Calendar.YEAR), Calendar.DECEMBER, 15);
        Calendar highSeasonEnd1 = new GregorianCalendar(arrivalCalendar.get(Calendar.YEAR) + 1, Calendar.JANUARY, 15);

        Calendar highSeasonStart2 = new GregorianCalendar(arrivalCalendar.get(Calendar.YEAR), Calendar.JUNE, 15);
        Calendar highSeasonEnd2 = new GregorianCalendar(arrivalCalendar.get(Calendar.YEAR), Calendar.JULY, 15);

        // Calculate Easter based on Western Christian calculation (simplified)
        Calendar easter = calculateEaster(arrivalCalendar.get(Calendar.YEAR));
        Calendar easterStart = (Calendar) easter.clone();
        easterStart.add(Calendar.DAY_OF_MONTH, -3); // Start on Holy Thursday
        Calendar easterEnd = (Calendar) easter.clone();
        easterEnd.add(Calendar.DAY_OF_MONTH, 3); // End on Easter Monday

        // Check days in high season for each period
        daysInHighSeason += getDaysInRange(arrivalCalendar, departureCalendar, highSeasonStart1, highSeasonEnd1);
        daysInHighSeason += getDaysInRange(arrivalCalendar, departureCalendar, highSeasonStart2, highSeasonEnd2);
        daysInHighSeason += getDaysInRange(arrivalCalendar, departureCalendar, easterStart, easterEnd);

        return daysInHighSeason;
    }


    private int getDaysInRange(Calendar arrival, Calendar departure, Calendar rangeStart, Calendar rangeEnd) {
        int days = 0;
        // Clone calendars to avoid modifying original dates
        Calendar start = (Calendar) arrival.clone();
        Calendar end = (Calendar) departure.clone();

        // Ensure start and end are within the high season range
        if (start.before(rangeStart)) {
            start = (Calendar) rangeStart.clone();
        }
        if (end.after(rangeEnd)) {
            end = (Calendar) rangeEnd.clone();
        }

        // Calculate the number of days in the range
        if (!start.after(end)) {
            while (!start.after(end)) {
                days++;
                start.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return days;
    }

    // Simplified calculation for Easter Sunday
    private Calendar calculateEaster(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        return new GregorianCalendar(year, month - 1, day);
    }

    public void saveTemporada(Temporada temporada) {
        temporadaRepo.save(temporada);
    }

    public Temporada getTemporadaById(Integer id) {
        return temporadaRepo.findById(id).orElse(null);
    }

    public Temporada findByTemporada(String temporada) {
        return temporadaRepo.findByTemporada(temporada);
    }
}
