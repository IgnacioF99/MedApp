package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.repository.HealthInsuranceRepository;

@Service
public class HealthInsuranceServices {
    
	@Autowired
    private HealthInsuranceRepository healthInsuranceRepository;

    public List<HealthInsurance> findAllHealthInsurances() {
        return healthInsuranceRepository.findAll(); // Esto devuelve una lista
    }

}
