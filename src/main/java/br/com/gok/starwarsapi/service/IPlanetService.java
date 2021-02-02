package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlanetService {
    PageResponse<PlanetDTO> listAll(Pageable pageable);
    PlanetDTO findByName(String name);
    PlanetDTO findById(Long id);
    PageResponse<PlanetDTO> filterByQuery(String query, Pageable pageable);
    void remove(Long id);
}
