package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.services.DoctorServices;
import com.coding.medapp.services.UserServices;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
    
	@Autowired
    private UserServices userServices;
    @Autowired
    private DoctorServices doctorServices;


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

    //listar usuarios
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
            model.addAttribute("roles", Rol.Roles);
            return "infoPatients.jsp";
        } else {
            return "redirect:/";
        }
    }

    @PutMapping("/patient/editRole/{id}")
    public String updatePatientRole(@PathVariable("id") Long id, @RequestParam(value = "role")String role, HttpSession session, Model model){
        System.out.println("el rol es "+ role);
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if(userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {   
            User userEdit = userServices.getUser(id);
            userEdit.setRole(role);
            userServices.saveRol(userTemp);
            if (role.equals("DOCTOR")) {
                doctorServices.newDoctor(id);
            } 
            return "redirect:/admin/userList";
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
            List<Doctor> doctorList = doctorServices.findAllDoctors();
            model.addAttribute("doctors", doctorList); 
            model.addAttribute("roles", Rol.Roles);
            return "infoDoctors.jsp";
        } else {
            return "redirect:/";
        }  
    }
    
    @PutMapping("/doctor/editRole/{id}")
    public String updateDoctorList(@PathVariable("id") Long id, @RequestParam(value = "role")String role, HttpSession session, Model model){
        System.out.println("el rol es "+ role);
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
        if(userTemp == null) {
            return "redirect:/login";
        }
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {   
            User userEdit = userServices.getUser(id);
            Doctor doctorRemove = userEdit.getDoctor2();
            userEdit.setRole(role);
            userServices.saveRol(userTemp);
            if (role.equals("USER")) {
                doctorServices.removeDoctor(doctorRemove);
            } 
            return "redirect:/admin/doctorList";
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
            model.addAttribute("admins", userList);
            //model.addAttribute("roles", Rol.Roles);
            return "infoAdmins.jsp";
        } else {
            return "redirect:/";
        }  
    }


    //Buscar usuario por dni
    @GetMapping("/admin/patient")
    public String adminUser(HttpSession session, @RequestParam(value = "dni") Integer dni, Model model){
        User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
        // =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[0])) {
            //Obtener Lista de pacientes
            List<User> userList = userServices.usrDni(dni);
            User user = userList.get(0);
            if (user.getRole().equals("USER")) {
                model.addAttribute("patients", userList); 
                model.addAttribute("roles", Rol.Roles);
                return "infoPatients.jsp";
            } else {
                return "redirect:/admin/userList";
            }
            
        } else {
            return "redirect:/";
        }  
    }
}
