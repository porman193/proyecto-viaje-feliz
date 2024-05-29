package com.viajefeliza.alquiler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viajefeliza.alquiler.services.AuthService;

import jakarta.servlet.http.HttpSession;



@Controller
public class AppController {
    @Autowired
    private AuthService authService;

    @GetMapping("/home")
    public String home() {
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (authService.login(username, password)) {
            System.out.println("Usuario autenticado: " + session.getAttribute("userAuth"));
            return "index"; // Redirecciona a una página después del inicio de sesión exitoso
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login/login"; // Muestra la página de inicio de sesión con un mensaje de error
        }
    }
    @GetMapping("/mostrar-datos-sesion")
    public String mostrarDatosSesion(HttpSession session, Model model) {
        // Obtener datos de la sesión
        String username = (String) session.getAttribute("userAuth");
        String userId = (String) session.getAttribute("nombres");

        // Imprimir los datos en la consola
        System.out.println("Username: " + username);
        System.out.println("UserID: " + userId);

        // Agregar los datos al modelo para que puedan ser mostrados en la vista
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);

        // Devolver la vista donde se mostrarán los datos de la sesión
        return "index";
    }
}
