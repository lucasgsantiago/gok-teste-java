package br.com.gok.templateapi.domain.postgres;

import java.util.List;

public interface IPlanetRepository {

    Planet save(Planet model);
    void delete(Planet model);
    List<Planet> get();
    Planet getByName(String name);
    Planet getById(Long id);
}
