package com.example.bookmyshowclone.repository;

import com.example.bookmyshowclone.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
}
