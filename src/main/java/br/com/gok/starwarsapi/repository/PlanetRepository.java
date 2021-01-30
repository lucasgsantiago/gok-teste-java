package br.com.gok.starwarsapi.repository;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlanetRepository implements IPlanetRepository {

    private final IPlanetJPARepository repository;

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
