package br.com.gok.templateapi.repository;

import br.com.gok.templateapi.domain.postgres.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanetJPARepository extends JpaRepository<Planet,Long> {

}
