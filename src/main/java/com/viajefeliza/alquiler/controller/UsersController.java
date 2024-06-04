package com.viajefeliza.alquiler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viajefeliza.alquiler.model.User;
import com.viajefeliza.alquiler.services.UserService;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    private User user;
    // Mostrar lista de usuarios
    @GetMapping("/usuarios")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "usuarios/usuarios"; // Nombre de la vista HTML para listar usuarios
    }

    // Formulario para editar usuario
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);


        model.addAttribute("user", user);
        
        return "usuarios/editar_usuarios"; // Nombre de la vista HTML para editar usuario
    }

    // Actualizar usuario
    @PostMapping("/edit")
    public String updateUser(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam String contrasena, @RequestParam String correo, @RequestParam Integer id) {
        User user= userService.getUserById(id);
            user.setApellidos(apellidos);
            user.setContrasena(contrasena);
            user.setDireccion(correo);
            user.setNombres(nombres);
         
        userService.updateUser(user);
        return "redirect:/usuarios"; // Redirige a la lista de usuarios después de la actualización
    }

    // Eliminar usuario
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/usuarios"; // Redirige a la lista de usuarios después de la eliminación
    }
}