package br.com.gok.starwarsapi.repository;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanetJPARepository extends JpaRepository<Planet,Long> {

}
