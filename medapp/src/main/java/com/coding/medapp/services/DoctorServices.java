package com.coding.medapp.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.coding.medapp.models.Doctor;
import com.coding.medapp.models.Speciality;
import com.coding.medapp.models.User;
import com.coding.medapp.repository.DoctorRepository;

@Service
public class DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserServices userServices;
    
    @Autowired
    @Lazy
    private SpecialityServices specialityServices;
    
    
    

    public Doctor newDoctor(Long id){
        User doctor = userServices.getUser(id);
        String num = "0";
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctor(doctor);
        newDoctor.setLicense(num);
        newDoctor.setAvailability("A");
        newDoctor.setEndTime(LocalTime.of(00,00));
        newDoctor.setStartTime(LocalTime.of(00, 00));
        return doctorRepository.save(newDoctor);
    }
    
    public List<Doctor> findDoctorsBySpeciality(Long specialityId) {
        Speciality mySpeciality = specialityServices.getSpeciality(specialityId);

        if (mySpeciality == null) {
            return Collections.emptyList(); // Si la especialidad no existe, devolver una lista vacía
        }

        List<Doctor> allDoctors = findAllDoctors();
        List<Doctor> filteredDoctors = new ArrayList<>();

        for (Doctor doctor : allDoctors) {
            if (doctor.getSpecialitiesDoctor().contains(mySpeciality)) {
                filteredDoctors.add(doctor);
            }
            
        }

        return filteredDoctors;
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

    //Eliminacion de doctor
    public void removeDoctor(Doctor doctorRemove){
        doctorRepository.delete(doctorRemove);
    }
}
	
	
	
	


