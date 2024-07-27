package com.coding.medapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Content;
import com.coding.medapp.repository.ContentRepository;

@Service
public class ContentServices {
    @Autowired
    private ContentRepository contentRepository;
    
    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }
    
    public Content getContent(Long id) {
    	return contentRepository.findById(id).orElse(null);
    }
    
    public Content updateContent(Content content) {
        // Guardar el contenido actualizado en la base de datos
        return contentRepository.save(content);
    }

}
