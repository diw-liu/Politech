package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.CensusBlock;

@Repository
public interface CensusBlockRepository extends CrudRepository<CensusBlock,String>{

}