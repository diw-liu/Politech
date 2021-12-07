package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.VotingAgePopulation;

@Repository
public interface VotingAgePopulationRepository extends CrudRepository<VotingAgePopulation,String>{

}