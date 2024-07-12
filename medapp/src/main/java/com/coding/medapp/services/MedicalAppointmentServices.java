package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.MedicalAppointmentRepository;

@Service
public class MedicalAppointmentServices {
    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

}
