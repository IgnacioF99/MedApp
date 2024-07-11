package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.HealthInsuranceRepository;

@Service
public class HealthInsuranceServices {
    @Autowired
    private HealthInsuranceRepository healthInsuranceRepository;

}
