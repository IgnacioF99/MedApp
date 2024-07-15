package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.MedicalAppointment;
import com.coding.medapp.models.User;

@Repository
public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findByPatient(User patient);
    List<MedicalAppointment> findByDoctor(User doctor);
}