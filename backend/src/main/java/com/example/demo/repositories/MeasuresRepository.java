package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Measures;


@Repository
public interface MeasuresRepository extends CrudRepository<Measures,Long>{

}