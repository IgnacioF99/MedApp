package com.coding.medapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.MedicalAppointment;

@Repository
public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment,Long>{

}
