package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.MedicalHistory;

@Repository
public interface MedicalHistoryRepository extends CrudRepository<MedicalHistory,Long> {

	public List<MedicalHistory> findAll();
	

	
}
