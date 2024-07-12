package com.coding.medapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.medapp.models.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content,Long>{

}
