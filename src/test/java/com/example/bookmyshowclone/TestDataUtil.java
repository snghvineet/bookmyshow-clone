package com.example.bookmyshowclone;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static City createTestCity1(Integer id) {
        return City.builder().name("Mumbai").id(id).build();
    }
    public static City createTestCity2(Integer id) {
        return City.builder().name("New Delhi").id(id).build();
    }

    public static Theatre createTestTheatre1(City city) {
        return Theatre.builder().name("Theatre 1").city(city).id(1).build();
    }

    public static Theatre createTestTheatre2(City city) {
        return Theatre.builder().name("Theatre 2").city(city).id(2).build();
    }
}
