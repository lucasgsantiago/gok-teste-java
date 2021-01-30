package br.com.gok.templateapi.repository;

import br.com.gok.templateapi.domain.postgres.IPlanetRepository;
import br.com.gok.templateapi.domain.postgres.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanetSDRepository implements IPlanetRepository {

    @Autowired
    private IPlanetJPARepository repository;

    @Override
    public Planet save(Planet model) {
        return repository.save(model);
    }

    @Override
    public void delete(Planet model) {
        repository.delete(model);
    }

    @Override
    public List<Planet> get() {
        return repository.findAll();
    }

    @Override
    public Planet getByName(String name) {
        return null;
    }

    @Override
    public Planet getById(Long id) {
        return null;
    }
}
