package com.example.bookmyshowclone.service.impl;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.repository.CityRepository;
import com.example.bookmyshowclone.service.CityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City createCity(City city) {
        return repository.save(city);
    }

    @Override
    public Optional<City> getCity(int cityId) {
        return repository.findById(cityId);
    }

    @Override
    public Iterable<City> getAllCities() {
        return repository.findAll();
    }
}
