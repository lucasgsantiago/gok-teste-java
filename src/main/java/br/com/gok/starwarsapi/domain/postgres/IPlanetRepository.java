package br.com.gok.starwarsapi.domain.postgres;

import br.com.gok.starwarsapi.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface IPlanetRepository {

    Planet save(Planet model);
    void delete(Planet planet);
    Page<Planet> getAll(Pageable pageable);
    Optional<Planet> findByName(String name);
    Optional<Planet> findById(Long id);
//    Page<Planet> findAll(Specification<Planet> specification, Pageable pageable);
    Page<Planet> filterByQuery(SearchCriteria searchCriteria, Pageable pageable);
    boolean existsByName(String name);
}
