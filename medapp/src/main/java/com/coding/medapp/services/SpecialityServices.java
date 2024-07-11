package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.SpecialityRepository;

@Service
public class SpecialityServices {
    @Autowired
    private SpecialityRepository specialityRepository;

}
