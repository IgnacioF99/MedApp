package com.coding.medapp.services;

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

    public Doctor saveDoctor(Long id){
        User doctor = userServices.getUser(id);
        Long num = 0L;
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctor(doctor);
        newDoctor.setLicense(num);
        return doctorRepository.save(newDoctor);
    }
}
