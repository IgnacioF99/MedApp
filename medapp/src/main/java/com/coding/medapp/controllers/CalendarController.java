package com.coding.medapp.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import com.coding.medapp.services.MedicalAppointmentService;

@Controller
public class CalendarController {

    @Autowired
    private MedicalAppointmentService appointmentService;

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "2024") int year, 
                              @RequestParam(defaultValue = "1") int month, 
                              Model model) {
        model.addAttribute("year", year);
        
        // Agregar lista de meses al modelo
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.format("%02d", i));
        }
        model.addAttribute("months", months);
        
        // Cargar citas médicas para el mes y año seleccionados
        List<MedicalAppointment> appointments = appointmentService.getAppointmentsByMonth(year, month);
        model.addAttribute("appointments", appointments);
        
        return "calendar.jsp"; 
    }

    
}