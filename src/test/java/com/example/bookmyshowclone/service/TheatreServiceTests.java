package com.example.bookmyshowclone.service;

import com.example.bookmyshowclone.TestDataUtil;
import com.example.bookmyshowclone.exception.CityNotFoundException;
import com.example.bookmyshowclone.models.City;
import com.example.bookmyshowclone.models.Theatre;
import com.example.bookmyshowclone.repository.TheatreRepository;
import com.example.bookmyshowclone.service.impl.TheatreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TheatreServiceTests {
    @Mock
    private TheatreRepository theatreRepository;
    @Mock
    private CityService cityService;
    @InjectMocks
    private TheatreServiceImpl theatreService;

    @Test
    public void testThatCityNotFoundExceptionIsThrown() {
        assertThatThrownBy(() -> theatreService.getAllTheatre(1))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("City with id: 1 not found");
        assertThatThrownBy(() -> theatreService.createTheatre(1, null))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("City with id: 1 not found");
    }

    @Test
    public void testThatGetCityAndFinAllByCityIsCalledWithCorrectParameters() {
        City testCity = TestDataUtil.createTestCity1(1);
        when(cityService.getCity(1)).thenReturn(Optional.of(testCity));
        theatreService.getAllTheatre(1);
        verify(cityService).getCity(1);
        verify(theatreRepository).findAllByCity(testCity);

    }

    @Test
    public void testThatAllTheatresCanBeRecalled() {
        City testCity = TestDataUtil.createTestCity1(1);
        when(cityService.getCity(1)).thenReturn(Optional.of(testCity));
        Theatre testTheatre1 = TestDataUtil.createTestTheatre1(testCity);
        Theatre testTheatre2 = TestDataUtil.createTestTheatre2(testCity);
        when(theatreRepository.findAllByCity(testCity)).thenReturn(List.of(testTheatre1, testTheatre2));
        Iterable<Theatre> result = theatreService.getAllTheatre(1);

        assertThat(result).hasSize(2).containsExactly(testTheatre1, testTheatre2);
    }

    @Test
    public void testThatGetCityAndSaveIsCalledWithCorrectParameters() {
        City testCity = TestDataUtil.createTestCity1(1);
        Theatre testTheatre = TestDataUtil.createTestTheatre1(null);
        when(cityService.getCity(1)).thenReturn(Optional.of(testCity));
        theatreService.createTheatre(1, testTheatre);

        verify(cityService).getCity(1);
        testTheatre.setCity(testCity);
        verify(theatreRepository).save(testTheatre);
    }

    @Test
    public void testThatTheatreCanBeCreatedAndRecalled() {
        City testCity = TestDataUtil.createTestCity1(1);
        Theatre testTheatre = TestDataUtil.createTestTheatre1(null);
        when(cityService.getCity(1)).thenReturn(Optional.of(testCity));
        when(theatreRepository.save(any(Theatre.class))).thenReturn(
                TestDataUtil.createTestTheatre1(testCity)
        );
        Theatre result = theatreService.createTheatre(1, testTheatre);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(testTheatre);
    }
}
