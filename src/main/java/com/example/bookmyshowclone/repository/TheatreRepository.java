package com.example.bookmyshowclone.repository;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;
import org.springframework.data.repository.CrudRepository;

public interface TheatreRepository extends CrudRepository<Theatre,Integer> {
    Iterable<Theatre> findAllByCity(City city);
}
