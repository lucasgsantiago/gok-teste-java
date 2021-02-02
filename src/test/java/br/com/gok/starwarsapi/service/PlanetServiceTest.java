package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.PlanetFactory;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.exception.NotFoundException;
import br.com.gok.starwarsapi.repository.PlanetJPARepository;
import br.com.gok.starwarsapi.util.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;
    @Mock
    private PlanetJPARepository planetRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Planet> page = new PageImpl<>(Stream.of(PlanetFactory.createValidPlanet()).collect(Collectors.toList()));

        BDDMockito.when(planetRepositoryMock.getAll(ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(planetRepositoryMock.filterByQuery(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(planetRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PlanetFactory.createValidPlanet()));

        BDDMockito.when(planetRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(PlanetFactory.createValidPlanet()));

        BDDMockito.doNothing().when(planetRepositoryMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("listAll returns list of planets inside page response object when successful")
    void listAll_ReturnsListOfPlanetsInsidePageResponseObject_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PageResponse<PlanetDTO> page = planetService.listAll(null);

        assertThat(page).isNotNull();

        assertThat(page.getElements())
                .isNotEmpty()
                .hasSize(1);

        assertThat(page.getElements().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns a planet when successful")
    void findById_ReturnsAPlanet_WhenSuccessful(){
        Long expectedId = PlanetFactory.createValidPlanetDTO().getId();

        PlanetDTO planet = planetService.findById(expectedId);

        assertThat(planet).isNotNull();
        assertThat(planet.getId()).isEqualTo(expectedId);
    }


    @Test
    @DisplayName("findById throws BadRequestException when planet is not found")
    void findById_ThrowsBadRequestException_WhenPlanetIsNotFound(){
        BDDMockito.when(planetRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> planetService.findById(1L));
    }

    @Test
    @DisplayName("findByName returns a planet when successful")
    void findByName_ReturnsAPlanet_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PlanetDTO planet = planetService.findByName(expectedName);

        assertThat(planet).isNotNull();
        assertThat(planet.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName throws BadRequestException when planet is not found")
    void findByName_ThrowsBadRequestException_WhenPlanetIsNotFound(){
        BDDMockito.when(planetRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> planetService.findByName("any"));
    }

    @Test
    @DisplayName("filterByPopulation returns list of planets inside page response object when successful")
    void filterByPopulation_ReturnsListOfPlanetsInsidePageResponseObject_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PageResponse<PlanetDTO> page = planetService.filterByQuery("population>1000",null);

        assertThat(page).isNotNull();

        assertThat(page.getElements())
                .isNotEmpty()
                .hasSize(1);

        assertThat(page.getElements().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("delete removes planet when successful")
    void delete_RemovesPlanet_WhenSuccessful(){

        assertThatCode(() ->planetService.delete(1L))
                .doesNotThrowAnyException();

    }

}