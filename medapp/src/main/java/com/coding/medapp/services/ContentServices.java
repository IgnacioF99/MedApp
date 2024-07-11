package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.repository.ContentRepository;

@Service
public class ContentServices {
    @Autowired
    private ContentRepository contentRepository;

}
