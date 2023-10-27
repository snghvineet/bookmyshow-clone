package com.example.bookmyshowclone.service;

import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.repository.CityRepository;
import com.example.bookmyshowclone.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTests {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    public void testThatSaveInRepositoryIsCalledWithCorrectParameters() {
        City city = City.builder()
                .name("Mumbai")
                .build();
        cityService.createCity(city);
        verify(cityRepository).save(city);
    }

    @Test
    public void testThatNewCityIsCreatedAndReturned() {
        City city = City.builder()
                .name("Mumbai")
                .build();
        when(cityRepository.save(city)).thenReturn(
                City.builder()
                        .name(city.getName())
                        .id(1)
                        .build()
        );
        City createdCity = cityService.createCity(city);
        System.out.println(createdCity.getId());
        assertThat(createdCity).isNotNull();
        assertThat(createdCity.getName()).isEqualTo(city.getName());
        assertThat(createdCity.getId()).isEqualTo(1);
    }

    @Test
    public void testThatFindByIdIsCalledInRepositoryWithCorrectParameters() {
        cityService.getCity(1);
        verify(cityRepository).findById(1);
    }

    @Test
    public void testThatGetCityReturnsTheCorrectCity() {
        City city = City.builder()
                .name("Mumbai")
                .id(1)
                .build();
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));
        Optional<City> result = cityService.getCity(1);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
    }

    @Test
    public void testThatFindAllInRepositoriesIsCalled() {
        cityService.getAllCities();
        verify(cityRepository).findAll();
    }

    @Test
    public void testThatAllCitiesCanBeRecalled() {
        City city1 = City.builder()
                .name("Mumbai")
                .id(1)
                .build();
        City city2 = City.builder()
                .name("New Delhi")
                .id(2)
                .build();
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));
        Iterable<City> result = cityService.getAllCities();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(city1, city2);
    }
}
