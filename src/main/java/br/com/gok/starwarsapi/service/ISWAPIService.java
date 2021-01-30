package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import feign.Param;

public interface ISWAPIService {
    SwapiPlanetDTO getPlanets();
    SwapiPlanetDTO getPlanetByName(@Param("name") String name);
}
