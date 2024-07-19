package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private UserServices userServices;

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
            model.addAttribute("rol", "nada");
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin/userList")
    public String adminUserList(HttpSession session, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<User> userList = userServices.findAllUserRol("USER");
            model.addAttribute("patients", userList);          
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }  
    }
    @GetMapping("/admin/doctorList")
    public String adminDoctorList(HttpSession session, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<User> userList = userServices.findAllUserRol("DOCTOR");
            model.addAttribute("patients", userList);          
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }  
    }

    @GetMapping("/admin/adminList")
    public String adminAdminList(HttpSession session, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<User> userList = userServices.findAllUserRol("ADMIN");
            model.addAttribute("patients", userList);          
            return "welcomeAdmin.jsp";
        } else {
            return "redirect:/";
        }  
    }

}
