package br.com.gok.starwarsapi.repository;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanetJPARepository implements IPlanetRepository {

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
    public Page<Planet> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Planet> findByName(String name) {
        return findByName(name);
    }

    @Override
    public Optional<Planet> findById(Long id) {
        return repository.findById(id);
    }
}
