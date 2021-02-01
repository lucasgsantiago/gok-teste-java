package br.com.gok.starwarsapi.domain.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlanetRepository {

    Planet save(Planet model);
    void delete(Planet model);
    Page<Planet> getAll(Pageable pageable);
    Optional<Planet> findByName(String name);
    Optional<Planet> findById(Long id);
}
