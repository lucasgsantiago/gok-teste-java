package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUpdatePlanetsFromSwapiService {
    Page<Planet> execute(Pageable pageable);
}
