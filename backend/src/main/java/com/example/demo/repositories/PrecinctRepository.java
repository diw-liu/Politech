package com.example.demo.repositories;
import com.example.demo.projections.summary.PrecinctSummaryProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Precinct;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecinctRepository extends CrudRepository<Precinct,String>{

    @Query("SELECT p FROM Precinct p WHERE p.id = ?1")
    Precinct findPrecinctTestId(String id);
    <T> Optional<T> findById(String id, Class<T> type);

//    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    List<PrecinctSummaryProjection> findByDistrictIdStartsWith(String name);
}