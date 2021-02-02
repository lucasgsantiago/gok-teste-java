package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.PlanetFactory;
import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.exception.NotFoundException;
import br.com.gok.starwarsapi.service.client.StarWarsPublicAPIClient;
import br.com.gok.starwarsapi.util.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
@ExtendWith(SpringExtension.class)
class SwapiFeignServiceTest {
    @InjectMocks
    private SwapiFeignService swapiFeignService;
    @Mock
    private EventPublisherService eventPublisher;
    @Mock
    private StarWarsPublicAPIClient swapiClient;

    @BeforeEach
    void setUp(){
        SwapiPageDTO page = new SwapiPageDTO(Stream.of(PlanetFactory.createValidSwapiPlanetDTO()).collect(Collectors.toList()));

        BDDMockito.when(swapiClient.getPlanets(ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(swapiClient.findPlanetByName(ArgumentMatchers.anyString())).thenReturn(page);

        BDDMockito.doNothing().when(eventPublisher).publishPlanetFoundFromSwapiEvent(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("getPlanets returns list of planets inside page response object from public api when successful")
    void getPlanets_ReturnsListOfPlanetsFromPublicAPI_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidSwapiPlanetDTO().getName();
        PageResponse<SwapiPlanetDTO> page = swapiFeignService.getPlanets(PageRequest.of(0, 1,  Sort.by(Sort.Order.asc("name"))));

        assertThat(page).isNotNull();

        assertThat(page.getElements())
                .isNotEmpty();

        assertThat(page.getElements().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findPlanetByName returns a planets from public api when successful")
    void findPlanetByName_ReturnsAPlanetFromPublicAPI_WhenSuccessful(){
        String expectedName = PlanetFactory.createValidSwapiPlanetDTO().getName();
        SwapiPlanetDTO planet = swapiFeignService.findPlanetByName(expectedName);

        assertThat(planet).isNotNull();
        assertThat(planet.getName()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("findPlanetByName Throws NotFoundException when not found planet")
    void findPlanetByName_ThrowsNotFoundException_WhenNotFoundByName(){
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> swapiFeignService.findPlanetByName("abcdefghi"));
    }
}