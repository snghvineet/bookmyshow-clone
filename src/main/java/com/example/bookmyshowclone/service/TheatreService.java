package com.example.bookmyshowclone.service;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;

public interface TheatreService {
    Iterable<Theatre> getAllTheatre(int cityId);
    Theatre createTheatre(int cityId, Theatre theatre);
}
