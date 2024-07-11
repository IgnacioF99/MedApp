package com.coding.medapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.User;
import com.coding.medapp.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
