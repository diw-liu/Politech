package com.example.demo.repositories;

import com.example.demo.model.County;
import com.example.demo.projections.summary.CountySummaryProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends CrudRepository<County, String> {
    List<County> findByState(String state);
}
