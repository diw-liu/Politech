package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.District;


@Repository
public interface DistrictRepository extends CrudRepository<District,Long>{

}