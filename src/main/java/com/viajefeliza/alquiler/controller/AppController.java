package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.RolUsuario;
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
<<<<<<< HEAD
            session.setAttribute("userAuth", username); // Asegúrate de establecer el atributo de sesión
            System.out.println("Usuario autenticado: " + session.getAttribute("userAuth"));
            return "admin/indexAdmin";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login/login";
=======
            RolUsuario rol= (RolUsuario) session.getAttribute("userRol");
            if("Administrador".equals(rol.getRol())){
                System.out.println("Usuario admin autenticado: " + session.getAttribute("userAuth"));
                return "admin/indexAdmin";
            }else if("Cliente".equals(rol.getRol())){
                System.out.println("Usuario cliente autenticado: " + session.getAttribute("userAuth"));
                return "index";
            }
>>>>>>> 778d19616a6e7fc4b18aabfa41da95850807d60f
        }
        model.addAttribute("error", "Credenciales incorrectas");
        return "login/login"; // Muestra la página de inicio de sesión con un mensaje de error
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
