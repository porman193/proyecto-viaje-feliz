package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.Fotografia;
import com.viajefeliza.alquiler.services.FotografiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/images-properties")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Fotografia fotografia = fotografiaService.getFotografiaById(id);

        if (fotografia != null && fotografia.getFotografia() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG); // Cambia el tipo de contenido según sea necesario
            return new ResponseEntity<>(fotografia.getFotografia(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
