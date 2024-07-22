package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.services.DoctorServices;
import com.coding.medapp.services.HealthInsuranceServices;
import com.coding.medapp.services.SpecialityServices;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;

@Controller
public class DoctorController {
	
	@Autowired
	private UserServices userServices;

    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private SpecialityServices specialityServices;

    @Autowired
    private HealthInsuranceServices insuranceServices;

    
    @GetMapping("/doctor")
    public String doctor(HttpSession session, Model model) {
	    // =====REVISAMOS SESION=========
	    User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null. userInSession es el nombre del atributo en el servicio de sesion
		if(userTemp == null) {
			return "redirect:/login";
		}
		// =====REVISAMOS SU ROL========
        if (userTemp.getRole().equals(Rol.Roles[2])) {
            return "welcomeDoctor.jsp";
        } else {
            return "redirect:/";
        }

    }
			
    @GetMapping("/doctor/{id}")
    public String profileDoctor(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession"); 
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (userTemp.getRole().equals(Rol.Roles[2])) {
            Doctor myDoctor = doctorServices.getDoctor(id);
            User myUser = userServices.getUser(id);
            model.addAttribute("user", myUser);
            model.addAttribute("doctor", myDoctor); // Asegúrate de añadir el usuario al modelo
            return "doctorProfile.jsp";
        } else {
            return "redirect:/";
        }
    
    }

    @GetMapping("/doctor/edit/{id}")
    public String editDoctorProfile(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        Doctor doctor = doctorServices.getDoctor(id);
        List<Speciality> specialties = specialityServices.findAllSpecialties();
        List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances(); 
        model.addAttribute("doctor", doctor);
        model.addAttribute("specialities", specialties);
        model.addAttribute("healthInsurances", healthInsurances);
        return "doctorProfileEdit.jsp";
    }

    @PutMapping("/doctor/update/{id}")
    public String updateProfile(@Valid @ModelAttribute("doctor") Doctor doctorUpdated, BindingResult result, HttpSession session, Model model,
                                @RequestParam(value = "specialitiesDoctor", required = false) Long specialityId,
                                @RequestParam(value = "insurance", required = false) Long insuranceId) {
        User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            List<Speciality> specialties = specialityServices.findAllSpecialties();
            List<HealthInsurance> healthInsurances = insuranceServices.findAllHealthInsurances(); 
            model.addAttribute("specialities", specialties);
            model.addAttribute("healthInsurances", healthInsurances);
            return "doctorProfileEdit.jsp";
        }
        if (userTemp.getRole().equals(Rol.Roles[2])) {
        	//Obtenemos el doctor
            Doctor existingDoctor = doctorServices.getDoctor(doctorUpdated.getId());
          	//Si selecciono alguna especialidad en el form usa el metodo de AddSpeciality
            if (specialityId != null) { 
                specialityServices.addSpeciality(existingDoctor.getId(), specialityId);
            }
            //Si selecciono alguna obra social - se la agrega
            if(insuranceId != null) {
            	insuranceServices.addInsurance(existingDoctor.getId(), insuranceId);
       	
            }
            //Setea las obras sociales al doc- recibiendo las que tiene el existing doctor
            doctorUpdated.setInsurance(existingDoctor.getInsurance());
            // Setea las especialidades existentes en el doctor actualizado
            doctorUpdated.setSpecialitiesDoctor(existingDoctor.getSpecialitiesDoctor());
            doctorServices.saveDoctor(doctorUpdated);
            return "redirect:/doctor/" + doctorUpdated.getId();
        } else {
            return "redirect:/";
        }
    }

}
