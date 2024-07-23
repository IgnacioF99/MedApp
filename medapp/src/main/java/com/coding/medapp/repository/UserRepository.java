package com.coding.medapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	//Listar todos los usuarios
	List<User> findAll(); 

	//Listar usuarios por rol
	List<User> findByRoleLike(String rol); 

	//Buscar usuario por email
	User findByEmail(String email);
	
	//buscar usuario por dni
	User findByDni(Integer dni);
	//List<User> findAllDni(Integer dni);
}
