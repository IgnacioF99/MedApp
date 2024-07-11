package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	List<User> findAll();   

}
