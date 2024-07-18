package com.coding.medapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import com.coding.medapp.services.MedicalAppointmentService;
import com.coding.medapp.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MedicalAppointmentController {
    @Autowired
    private MedicalAppointmentService appointmentService;
    @Autowired
    private UserServices userService;

    @GetMapping("/appointment")
    public String showAppointments(HttpSession session, Model model) {
        User userInSession = (User) session.getAttribute("userInSession");
        List<MedicalAppointment> appointments = appointmentService.getAppointmentsByPatient(userInSession);
        model.addAttribute("appointments", appointments);
        return "appointments.jsp";
    }

    @GetMapping("/newAppointment")
    public String newAppointment(@ModelAttribute("appointment") MedicalAppointment appointment, Model model, HttpSession session) {
        User userInSession = (User) session.getAttribute("userInSession");
        model.addAttribute("doctors", userService.findAllUsers());
        return "newAppointment.jsp";
    }

    @PostMapping("/createAppointment")
    public String createAppointment(@Valid @ModelAttribute("appointment") MedicalAppointment appointment, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "newAppointment.jsp";
        }
        User userInSession = (User) session.getAttribute("userInSession");
        appointment.setPatient(userInSession);
        appointmentService.createAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment created successfully!");
        return "redirect:/appointments";
    }

    @GetMapping("/appointment/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model) {
        MedicalAppointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "editAppointment.jsp";
    }

    @PostMapping("/appointment/update/{id}")
    public String updateAppointment(@PathVariable("id") Long id, @Valid @ModelAttribute("appointment") MedicalAppointment appointment, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editAppointment.jsp";
        }
        appointmentService.updateAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment updated successfully!");
        return "redirect:/appointments";
    }

    @GetMapping("/appointment/cancel/{id}")
    public String cancelAppointment(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        MedicalAppointment appointment = appointmentService.getAppointmentById(id);
        appointmentService.cancelAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment cancelled successfully!");
        return "redirect:/appointments";
    }
}