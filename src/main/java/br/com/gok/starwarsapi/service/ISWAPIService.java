package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ISWAPIService {
    PageResponse<SwapiPlanetDTO> getPlanets(Pageable pageable);
    SwapiPlanetDTO findPlanetByName(String name);
}
