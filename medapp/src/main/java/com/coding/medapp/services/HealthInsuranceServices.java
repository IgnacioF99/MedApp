package com.coding.medapp.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.HealthInsurance;
import com.coding.medapp.repository.HealthInsuranceRepository;

@Service
public class HealthInsuranceServices {
    
	@Autowired
    private HealthInsuranceRepository healthInsuranceRepository;
	
	@Autowired
	private DoctorServices doctorServices;
	
	public HealthInsurance saveHealthInsurance(HealthInsurance insurance) {
		return healthInsuranceRepository.save(insurance);
	}
	
	public HealthInsurance getHealthInsurance(Long id) {
		return healthInsuranceRepository.findById(id).orElse(null);
	}

    public List<HealthInsurance> findAllHealthInsurances() {
        return healthInsuranceRepository.findAll(); // Esto devuelve una lista
    }
    
    public void addInsurance(Long doctorId, Long insuranceId) {
    	Doctor myDoctor = doctorServices.getDoctor(doctorId);
    	HealthInsurance myInsurance = getHealthInsurance(insuranceId);
    	
    	//Verifica si el doctor ya tiene la obra social
    	if(myDoctor.getInsurance().contains(myInsurance)) {
    		
    		return;
    	}
    	
    	myDoctor.getInsurance().add(myInsurance);
    	doctorServices.saveDoctor(myDoctor);
    }
    
    public void deleteHealthInsurance(Long id) {
        healthInsuranceRepository.deleteById(id);
    }
    
    public List<HealthInsurance> findAllHealthInsurancesSorted() {
        List<HealthInsurance> insurances = findAllHealthInsurances();
        Collections.sort(insurances, Comparator.comparing(HealthInsurance::getName));
        return insurances;
    }


}
