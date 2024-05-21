package com.viajefeliza.alquiler.services;

import com.viajefeliza.alquiler.model.User;
import com.viajefeliza.alquiler.repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    HttpSession session;

    public boolean login(String username, String password) {
        User user = userRepo.findByEmail(username);
        if(user != null && user.getContrasena().equals(password)){
            session.setAttribute("userAuth", user);
            return true;
        };
        return false;
    }

}
