package br.com.gok.starwarsapi.service.client;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import feign.Param;
import feign.RequestLine;

public interface StarWarsPublicAPIClient {

    @RequestLine("GET /planets/")
    SwapiPageDTO getPlanets();

    @RequestLine("GET /planets/?search={name}")
    SwapiPageDTO findPlanetByName(@Param("name") String name);

}
