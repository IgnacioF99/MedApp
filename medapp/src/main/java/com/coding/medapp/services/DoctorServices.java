package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.repository.DoctorRepository;

@Service
public class DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;


	public Doctor getDoctor(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}

	public void saveDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}
	
	public List<Doctor> findAllDoctors(){
		return doctorRepository.findAll();
	}
}
