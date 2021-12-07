package com.example.demo.repositories;

import com.example.demo.model.VotingAgeMeasures;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingAgeMeasuresRepository extends CrudRepository<VotingAgeMeasures, String> {

}
