package br.com.gok.starwarsapi.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Service;

import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
@Service
public class SwapiFeignService implements ISWAPIService {

    private static final String SWAPI_URL = "https://swapi.dev/api";

    private final StarWarsPublicAPIClient swapi = Feign.builder()
            .decoder(new GsonDecoder())
            .target(StarWarsPublicAPIClient.class, SWAPI_URL);

    @Override
    public SwapiPlanetDTO getPlanets() {
        return swapi.getPlanets();
    }

    @Override
    public SwapiPlanetDTO getPlanetByName(String name) {
        return swapi.getPlanetByName(name);
    }
}
