package com.coding.medapp.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.MedicalAppointmentRepository;

@Service
public class MedicalAppointmentService {
    @Autowired
    private MedicalAppointmentRepository appointmentRepository;

    public MedicalAppointment createAppointment(MedicalAppointment appointment) {
    	System.out.println("Received appointment: ");
        return appointmentRepository.save(appointment);
    }

    public List<MedicalAppointment> getAppointmentsByPatient(User patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public List<MedicalAppointment> getAppointmentsByDoctor(User doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    public MedicalAppointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public void deleteAppointment(MedicalAppointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public void updateAppointment(MedicalAppointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(MedicalAppointment appointment) {
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }

    public List<MedicalAppointment> getAppointmentsByMonth(int year, int month) {
        // Obtener el primer y último día del mes
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        // Buscar citas entre el primer y último día del mes
        return appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
    }
}