package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPlanetService {
    PageResponse<PlanetDTO> listAll(Pageable pageable);
    List<PlanetDTO> filterByPopulation();
    PlanetDTO findByName(String name);
    PlanetDTO findById(Long id);
    void remove(Long id);
}
