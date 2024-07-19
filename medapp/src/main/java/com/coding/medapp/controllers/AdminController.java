package com.coding.medapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
    //Inicio de Admin
    @GetMapping("/admin")
    public String welcomeAdmin(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }
    }

}
