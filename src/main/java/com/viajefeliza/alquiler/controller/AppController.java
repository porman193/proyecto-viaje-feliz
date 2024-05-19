package com.viajefeliza.alquiler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class AppController {
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "login";
    }
}
