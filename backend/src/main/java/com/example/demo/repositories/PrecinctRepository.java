package com.example.demo.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Precinct;

@Repository
public interface PrecinctRepository extends CrudRepository<Precinct,String>{

    @Query("SELECT p FROM Precinct p WHERE p.id = ?1")
    Precinct findPrecinctTestId(String id);
}