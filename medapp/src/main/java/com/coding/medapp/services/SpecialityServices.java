package com.coding.medapp.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.repository.SpecialityRepository;

@Service
public class SpecialityServices {
    
	@Autowired
    private SpecialityRepository specialityRepository;
	
	@Autowired
	private DoctorServices doctorServices;
	
	public Speciality saveSpeciality(Speciality speciality) {
		return specialityRepository.save(speciality);
	}
    
    public List<Speciality> findAllSpecialties(){
    	return specialityRepository.findAll();
    }
    
    public Speciality getSpeciality(Long id) {
    	return specialityRepository.findById(id).orElse(null);
    }
    	
    public void addSpeciality(Long doctorId, Long specialityId) {
        Doctor myDoctor = doctorServices.getDoctor(doctorId);
        Speciality mySpeciality = getSpeciality(specialityId);

        // Asigna la nueva especialidad al doctor
        myDoctor.setDoctorSpeciality(mySpeciality);

        // Guarda los cambios en la base de datos
        doctorServices.saveDoctor(myDoctor);
    }

    
    public void deleteSpeciality(Long id) {
        specialityRepository.deleteById(id);
    }

    
    public List<Speciality> findAllSpecialitiesSorted() {
        List<Speciality> specialities = specialityRepository.findAll();
        Collections.sort(specialities, Comparator.comparing(Speciality::getName));
        return specialities;
    }
    
}
