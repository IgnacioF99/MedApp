package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Speciality;
import com.coding.medapp.repository.SpecialityRepository;

@Service
public class SpecialityServices {
    
	@Autowired
    private SpecialityRepository specialityRepository;
    
    public List<Speciality> findAllSpecialties(){
    	return specialityRepository.findAll();
    }
    
    public Speciality getSpeciality(Long id) {
    	return specialityRepository.findById(id).orElse(null);
    }
    
    

}
