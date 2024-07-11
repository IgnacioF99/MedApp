package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.DoctorRepository;

@Service
public class DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;

}
