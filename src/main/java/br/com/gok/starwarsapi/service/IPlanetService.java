package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.Planet;

import java.util.List;

public interface IPlanetService {
    List<Planet> listAll();
    List<Planet> filterByPopulation();
    Planet findByName(String name);
    Planet findById(Long id);
    void remove(Long id);
}
