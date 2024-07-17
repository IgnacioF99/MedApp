package com.coding.medapp.services;

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
}