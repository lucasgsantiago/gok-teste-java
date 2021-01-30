package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import feign.Param;
import feign.RequestLine;

public interface StarWarsPublicAPIClient {

    @RequestLine("GET /planets/")
    SwapiPlanetDTO getPlanets();

    @RequestLine("GET /planets/?search={name}")
    SwapiPlanetDTO getPlanetByName(@Param("name") String name);

}
