package com.coding.medapp.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.Rol;
import com.coding.medapp.models.User;
import com.coding.medapp.services.MedicalAppointmentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AppointmentController {
	
	@Autowired
	private MedicalAppointmentService appointmentServices;

	@PostMapping("/appointments/create")
	public String newAppointment(@Valid @ModelAttribute("newAppointment")MedicalAppointment newAppointment,
								
								
								HttpSession session, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
        if (userTemp == null) {
            return "redirect:/login";
        }if (userTemp.getRole().equals(Rol.Roles[1])) {
        	newAppointment.setStatus("Scheduled");
        	appointmentServices.createAppointment(newAppointment);
        	return "redirect:/patient" + userTemp.getId();
        }else {

        	return "redirect:/";
        }


	}
}
