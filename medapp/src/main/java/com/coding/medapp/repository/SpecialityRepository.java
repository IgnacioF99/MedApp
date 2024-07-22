package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.Speciality;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality,Long>{

	List<Speciality> findAll();
	
	
}
