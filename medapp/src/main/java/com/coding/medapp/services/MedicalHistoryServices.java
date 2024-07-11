package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryServices {
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

}
