package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.MedicalHistory;
import com.coding.medapp.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryServices {
    
	@Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory getMedicalHistory(Long id) {
    	return medicalHistoryRepository.findById(id).orElse(null);
    }
    public List<MedicalHistory> findAllHistory(){
    	return medicalHistoryRepository.findAll();
    }
}
