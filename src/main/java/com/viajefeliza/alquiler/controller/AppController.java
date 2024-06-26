package com.viajefeliza.alquiler.controller;

import com.viajefeliza.alquiler.model.Reserva;
import com.viajefeliza.alquiler.model.RolUsuario;
import com.viajefeliza.alquiler.model.TelefonosUsuario;
import com.viajefeliza.alquiler.model.User;
import com.viajefeliza.alquiler.services.ReservaService;
import com.viajefeliza.alquiler.services.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viajefeliza.alquiler.services.AuthService;

import jakarta.servlet.http.HttpSession;


import java.util.Date;
import java.util.List;


@Controller
public class AppController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private DatabaseController databaseController;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        RolUsuario rol= (RolUsuario) session.getAttribute("userRol");
        // Verificar si el usuario es administrador
        if (rol!= null){
            if (rol.getRol().equals("Administrador")) {
                return "admin/indexAdmin"; // Redirigir al inicio de administrador
            } else {
                return "index"; // Redirigir al inicio normal
            }
        }
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
            RolUsuario rol= (RolUsuario) session.getAttribute("userRol");
            if("Administrador".equals(rol.getRol())){
                System.out.println("Usuario admin autenticado: " + session.getAttribute("userAuth"));
                return "admin/indexAdmin";
            }else if("Cliente".equals(rol.getRol())){
                System.out.println("Usuario cliente autenticado: " + session.getAttribute("userAuth"));
                return "index";
            }
        }
        model.addAttribute("error", "Credenciales incorrectas");
        return "login/login"; // Muestra la página de inicio de sesión con un mensaje de error

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

    @GetMapping("/user-profile")
    public String userProfile(HttpSession session, Model model) {
        // Obtener datos de la sesión
        User user = (User) session.getAttribute("userAuth");

        List<Reserva> reservas = reservaService.getReservasByUser(user);
        List<TelefonosUsuario> telefonosUsuario = telefonoService.getTelefonosByUsuario(user);
        System.out.println("Reservas del usuario: " + reservas);
        model.addAttribute("reservas", reservas);
        model.addAttribute("standardDate", new Date());
        model.addAttribute("telefonos", telefonosUsuario);
        return "user/userProfile";
        }
    // Método para verificar si el usuario es administrador
    private boolean isAdmin(HttpSession session) {
        // Aquí puedes implementar la lógica para verificar si el usuario es administrador
        // Por ejemplo, podrías comprobar si el rol del usuario es "admin"
        // Por ahora, supongamos que si el atributo "isAdmin" de la sesión es true, el usuario es administrador
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        return isAdmin != null && isAdmin;
    }
}
