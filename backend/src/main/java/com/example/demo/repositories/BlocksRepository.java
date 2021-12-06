package com.example.demo.repositories;

import com.example.demo.model.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocksRepository extends CrudRepository<Block, String> {
    <T> T findById(String id, Class<T> type);
}
