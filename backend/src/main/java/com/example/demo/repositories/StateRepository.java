package com.example.demo.repositories;

import com.example.demo.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends CrudRepository<State, String> {
    <T> T findByName(String name, Class<T> tClass);
    <T> Optional<T> findById(String id, Class<T> type);
}
