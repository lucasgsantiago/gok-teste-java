package br.com.gok.starwarsapi.service.client;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DisplayName("Tests of API Client")
class StarWarsPublicAPIClientTest {
    private static final String SWAPI_URL = "https://swapi.dev/api";

    private final StarWarsPublicAPIClient swapiClient = Feign.builder()
            .decoder(new GsonDecoder())
            .target(StarWarsPublicAPIClient.class, SWAPI_URL);
    @Test
    @DisplayName("getPlanets returns list of planets from public api when successful")
    void getPlanets_ReturnsListOfPlanetsFromPublicAPI_WhenSuccessful(){
        SwapiPageDTO pagePlanet = swapiClient.getPlanets(1);
        assertThat(pagePlanet).isNotNull();
        assertThat(pagePlanet.getResults()).isNotEmpty();
    }

    @Test
    @DisplayName("findPlanetByName returns a planet from public api when successful")
    void findPlanetByName_ReturnsAPlanetFromPublicAPI_WhenSuccessful(){
        SwapiPageDTO pagePlanet = swapiClient.findPlanetByName("Tatooine");
        assertThat(pagePlanet).isNotNull();
    }
}