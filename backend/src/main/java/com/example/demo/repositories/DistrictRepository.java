package com.example.demo.repositories;
import com.example.demo.model.*;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DistrictRepository extends CrudRepository<District, String>{
    @Query("SELECT d.id, d.districting.id, d.geometryString, d.population, d.vap, d.election, d.cd FROM District d " +
            "WHERE d.id = ?1")
    List<Object[]> findNoGeometryById(String id);
    <T> Optional<T> findById(String id, Class<T> type);
}