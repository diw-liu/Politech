package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Precinct;


@Repository
public interface PrecinctRepository extends CrudRepository<Precinct,Long>{

}