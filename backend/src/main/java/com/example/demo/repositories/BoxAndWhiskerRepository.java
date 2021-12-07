package com.example.demo.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.BoxAndWhisker;

@Repository
public interface BoxAndWhiskerRepository extends CrudRepository<BoxAndWhisker,String>{

}