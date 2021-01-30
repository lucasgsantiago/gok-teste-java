package br.com.gok.templateapi.service;

import br.com.gok.templateapi.dto.SwapiPlanetDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class ExternalAPIService {

    @Autowired
    private ISWAPIService swapiService;

    @Test
    public void whenAValidPlanetNameIsPassedShouldReturnAPlanet(){
        SwapiPlanetDTO planet = swapiService.getPlanetByName("Tatooine");
        assertThat(planet,is(notNullValue()));
    }
}
