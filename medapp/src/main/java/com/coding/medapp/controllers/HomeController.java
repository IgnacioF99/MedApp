package com.coding.medapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;

import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.jsp"; 
    }

    @GetMapping("/inicio")
    public String inicio(HttpSession session, Model model){
        // =====REVISAMOS SESION=========
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====Revisamos su rol
        if (userTemp.getRole().equals(Rol.Roles[1])) {
            return "redirect:/paciente";
        } else if (userTemp.getRole().equals(Rol.Roles[0])) {
            return "redirect:/admin";
        } else if (userTemp.getRole().equals(Rol.Roles[2])){
            return "redirect:/doctor";
        }

        return "redirect:/";

    }

}
