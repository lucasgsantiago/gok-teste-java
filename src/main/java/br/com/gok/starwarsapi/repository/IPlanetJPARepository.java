package br.com.gok.starwarsapi.repository;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IPlanetJPARepository extends JpaRepository<Planet,Long>, JpaSpecificationExecutor<Planet> {
    Optional<Planet> findByName(String name);

    @Override
    Page<Planet> findAll(Specification<Planet> specification, Pageable pageable);
}
