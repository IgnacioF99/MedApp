package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.DoctorRepository;

@Service
public class DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserServices userServices;

    public Doctor newDoctor(Long id){
        User doctor = userServices.getUser(id);
        String num = "0";
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctor(doctor);
        newDoctor.setLicense(num);
        newDoctor.setAvailability("A");
        return doctorRepository.save(newDoctor);
    }

	public Doctor getDoctor(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}

    public Doctor saveDoctor(Doctor newDoctor){
        return doctorRepository.save(newDoctor);
    }

	public List<Doctor> findAllDoctors(){
		return doctorRepository.findAll();
	}

}
