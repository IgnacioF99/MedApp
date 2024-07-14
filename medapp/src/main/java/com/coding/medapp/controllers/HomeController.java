package com.coding.medapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
			return "redirect:/";
		}

        //Revisar si es admin o doctor!

        return "inicio.jsp";
    }
}
