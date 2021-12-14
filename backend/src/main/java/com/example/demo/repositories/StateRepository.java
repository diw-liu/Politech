package com.example.demo.repositories;

import com.example.demo.model.State;
import com.example.demo.projections.StateGeometryProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends CrudRepository<State, String> {
    <T> T findByName(String name, Class<T> tClass);
    <T> Optional<T> findById(String id, Class<T> type);

    List<StateGeometryProjection> findAllProjectedBy();
}
