package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.HealthInsurance;

@Repository
public interface HealthInsuranceRepository extends CrudRepository<HealthInsurance,Long>{

	
	List<HealthInsurance> findAll();
}


