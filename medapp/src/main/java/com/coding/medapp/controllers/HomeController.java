package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.services.SpecialityServices;

import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {
    @Autowired
    private SpecialityServices specialityServices;

    @GetMapping("/")
    public String home(Model model) {
        List<Speciality> specialityList = specialityServices.findAllSpecialties();
        model.addAttribute("specialities", specialityList);
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
            return "redirect:/patient";
        } else if (userTemp.getRole().equals(Rol.Roles[0])) {
            return "redirect:/admin";
        } else if (userTemp.getRole().equals(Rol.Roles[2])){
            return "redirect:/doctor";
        }

        return "redirect:/";


    }
    

}
