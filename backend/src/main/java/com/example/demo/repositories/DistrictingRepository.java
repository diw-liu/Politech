package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Districting;

@Repository
public interface DistrictingRepository extends CrudRepository<Districting, String>{

}