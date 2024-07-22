package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long>{

	List<Doctor> findAll();
}
