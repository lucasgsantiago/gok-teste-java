package br.com.gok.starwarsapi.domain.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlanetRepository {

    Planet save(Planet model);
    void delete(Planet model);
    Page<Planet> getAll(Pageable pageable);
    Planet getByName(String name);
    Planet getById(Long id);
}
