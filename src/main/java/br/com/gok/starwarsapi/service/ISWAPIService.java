package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;

public interface ISWAPIService {
    PageResponse<SwapiPlanetDTO> getPlanets();
    SwapiPlanetDTO findPlanetByName(String name);
}
