package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Election;

@Repository
public interface ElectionRepository extends CrudRepository<Election, String>{

}