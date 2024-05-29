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
    
    @GetMapping("/admin")
    public String adminPage() {
        return "admin/indexAdmin";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (authService.login(username, password)) {
            session.setAttribute("userAuth", username); // Asegúrate de establecer el atributo de sesión
            System.out.println("Usuario autenticado: " + session.getAttribute("userAuth"));
            return "admin/indexAdmin";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login/login";
        }
    }
    
    @GetMapping("/mostrar-datos-sesion")
    public String mostrarDatosSesion(HttpSession session, Model model) {
        String username = (String) session.getAttribute("userAuth");
        String userId = (String) session.getAttribute("nombres");
        
        System.out.println("Username: " + username);
        System.out.println("UserID: " + userId);
        
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);
        
        return "index";
    }
}
