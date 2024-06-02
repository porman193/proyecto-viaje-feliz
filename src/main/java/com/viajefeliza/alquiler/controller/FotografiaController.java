package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.Fotografia;
import com.viajefeliza.alquiler.model.Property;
import com.viajefeliza.alquiler.services.FotografiaService;
import com.viajefeliza.alquiler.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/images-properties")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Property propiedad = propertyService.getPropertyById(id);
        List<Fotografia> fotografias = fotografiaService.getFotografiaByPropertyId(propiedad);
        System.out.println("fotos"+fotografias);
        for(Fotografia fotografia : fotografias) {
            if (fotografia != null && fotografia.getFotografia() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG); // Cambia el tipo de contenido según sea necesario
                return new ResponseEntity<>(fotografia.getFotografia(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
