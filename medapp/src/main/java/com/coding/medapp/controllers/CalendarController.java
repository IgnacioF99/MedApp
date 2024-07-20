package com.coding.medapp.controllers;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import com.coding.medapp.services.MedicalAppointmentService;
import com.coding.medapp.services.UserServices;
import com.coding.medapp.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private MedicalAppointmentService appointmentService;

    @Autowired
    private UserServices userService;

    @Autowired
    private DoctorServices doctorService;

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(defaultValue = "2024") int year, 
                              @RequestParam(defaultValue = "1") int month, 
                              Model model) {
        model.addAttribute("year", year);
        
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.format("%02d", i));
        }
        model.addAttribute("months", months);
        
        List<MedicalAppointment> appointments = appointmentService.getAppointmentsByMonth(year, month);
        model.addAttribute("appointments", appointments);
        
        return "calendar";
    }

    @PostMapping("/appointments/create")
    public String createAppointment(@RequestParam String title, 
                                    @RequestParam String date, 
                                    @RequestParam String description,
                                    @RequestParam Long doctorId, 
                                    @RequestParam Long patientId) { 
        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setStatus("Scheduled");
        appointment.setAppointmentDate(LocalDate.parse(date)); 
        appointment.setAppointmentTime(LocalTime.now()); 
        
        Doctor doctor = doctorService.getDoctorById(doctorId);
        User patient = userService.getUser(patientId);
        
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        appointmentService.createAppointment(appointment);
        
        return "redirect:/calendar"; 
    }
}
