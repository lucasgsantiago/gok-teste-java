package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.PlanetFactory;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.service.PlanetService;
import br.com.gok.starwarsapi.util.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
class PlanetResourceTest {

    @InjectMocks
    private PlanetResource planetResource;
    @Mock
    private PlanetService planetServiceMock;

    @BeforeEach
    void setUp(){
        PageResponse<PlanetDTO> page = new PageResponse<PlanetDTO>(1,1,1,1,
                Stream.of(PlanetFactory.createValidPlanetDTO()).collect(Collectors.toList()));
        BDDMockito.when(planetServiceMock.listAll(ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(planetServiceMock.filterByQuery(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(planetServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(PlanetFactory.createValidPlanetDTO());

        BDDMockito.when(planetServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(PlanetFactory.createValidPlanetDTO());

        BDDMockito.doNothing().when(planetServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("get returns list of planets inside page response object when successful")
    void get_ReturnsListOfPlanetsInsidePageResponseObject_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PageResponse<PlanetDTO> page = planetResource.get(null);

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

        PlanetDTO planet = planetResource.findById(expectedId);

        assertThat(planet).isNotNull();
        assertThat(planet.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a planet when successful")
    void findByName_ReturnsAPlanet_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PlanetDTO planet = planetResource.findByName(expectedName);

        assertThat(planet).isNotNull();
        assertThat(planet.getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("filterByPopulation returns list of planets inside page response object when successful")
    void filterByPopulation_ReturnsListOfPlanetsInsidePageResponseObject_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidPlanetDTO().getName();

        PageResponse<PlanetDTO> page = planetResource.filterByPopulation("1",null);

        assertThat(page).isNotNull();

        assertThat(page.getElements())
                .isNotEmpty()
                .hasSize(1);

        assertThat(page.getElements().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("delete removes planet when successful")
    void delete_RemovesPlanet_WhenSuccessful(){

        assertThatCode(() ->planetResource.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = planetResource.delete(1L);

        assertThat(entity).isNotNull();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}