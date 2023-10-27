package com.example.bookmyshowclone.service.impl;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;
import com.example.bookmyshowclone.repository.TheatreRepository;
import com.example.bookmyshowclone.service.CityService;
import com.example.bookmyshowclone.service.TheatreService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class TheatreServiceImpl implements TheatreService {
    private final TheatreRepository theatreRepository;
    private final CityService cityService;

    public TheatreServiceImpl(TheatreRepository theatreRepository, CityService cityService) {
        this.theatreRepository = theatreRepository;
        this.cityService = cityService;
    }

    @Override
    public Iterable<Theatre> getAllTheatre(int cityId) {
        Optional<City> cityOptional = cityService.getCity(cityId);
        if (cityOptional.isEmpty()) {
            throw new RuntimeException("City with id: " + cityId + " not found");
        }
        City city = cityOptional.get();
        log.info("Finding all theatres for " + city.getName());
        return theatreRepository.findAllByCity(city);
    }

    @Override
    public Theatre createTheatre(int cityId, Theatre theatre) {
//        log.info(cityId + " " + theatre.toString());
        Optional<City> cityOptional = cityService.getCity(cityId);
        if (cityOptional.isEmpty()) {
            log.info("City with id: " + cityId + " not found");
            throw new RuntimeException("City with id: " + cityId + " not found");
        }
        City city = cityOptional.get();
        theatre.setCity(city);
        theatreRepository.save(theatre);
        return theatre;
    }
}
