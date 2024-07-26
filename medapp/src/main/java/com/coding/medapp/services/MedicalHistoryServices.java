package com.coding.medapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.MedicalHistory;
import com.coding.medapp.models.User;
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
    
    public MedicalHistory getOrCreateMedicalHistoryForUser(Long userId) {
        // Buscar un MedicalHistory para el paciente con userId
        Optional<MedicalHistory> medicalHistoryOpt = medicalHistoryRepository.findById(userId);

        if (medicalHistoryOpt.isPresent()) {
            return medicalHistoryOpt.get();
        } else {
            // Crear un nuevo MedicalHistory si no existe
            MedicalHistory newMedicalHistory = new MedicalHistory();
            return medicalHistoryRepository.save(newMedicalHistory);
        }
    }

    public MedicalHistory saveMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }
}
