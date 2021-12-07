package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Population;

@Repository
public interface PopulationRepository extends CrudRepository<Population,String>{

}