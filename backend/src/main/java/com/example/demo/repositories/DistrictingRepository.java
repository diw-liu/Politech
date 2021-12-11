package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Districting;

import java.util.Optional;

@Repository
public interface DistrictingRepository extends CrudRepository<Districting, String>{
    <T> Optional<T> findById(String id, Class<T> type);
}