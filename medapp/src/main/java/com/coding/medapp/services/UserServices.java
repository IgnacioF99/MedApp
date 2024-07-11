package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    
}
