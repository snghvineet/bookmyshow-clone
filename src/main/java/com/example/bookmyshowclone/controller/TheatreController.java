package com.example.bookmyshowclone.controller;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;
import com.example.bookmyshowclone.service.TheatreService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/theatres/{cityId}")
@Log
public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public Iterable<Theatre> retrieveAllTheatres(@PathVariable int cityId) {

        return theatreService.getAllTheatre(cityId);
    }

    @PostMapping
    public Theatre createTheatre(@PathVariable int cityId, @RequestBody Theatre theatre) {

        return theatreService.createTheatre(cityId, theatre);
    }
}
