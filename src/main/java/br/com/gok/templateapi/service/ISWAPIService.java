package br.com.gok.templateapi.service;

import br.com.gok.templateapi.dto.SwapiPlanetDTO;
import feign.Param;

public interface ISWAPIService {
    SwapiPlanetDTO getPlanets();
    SwapiPlanetDTO getPlanetByName(@Param("name") String name);
}
