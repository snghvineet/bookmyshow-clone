package com.example.bookmyshowclone.service;

import com.example.bookmyshowclone.models.City;

import java.util.Optional;

public interface CityService {
    Iterable<City> getAllCities();

    City createCity(City city);

    Optional<City> getCity(int cityId);

}
