package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService implements IPlanetService {
    private final IPlanetRepository repository;

    @Override
    public List<Planet> listAll() {
        return repository.get();
    }

    @Override
    public List<Planet> filterByPopulation() {
        return null;
    }

    @Override
    public Planet findByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public Planet findById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void remove(Long id) {
        repository.delete(new Planet().builder().id(id).build());
    }
}
