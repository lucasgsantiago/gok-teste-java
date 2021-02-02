package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.service.event.PlanetFoundFromSwapiEvent;
import br.com.gok.starwarsapi.util.PlanetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePlanetsFromSwapiService{
    private final IPlanetRepository repository;
    private PlanetMapper mapper = PlanetMapper.INSTANCE;

    @EventListener
    public void handler(PlanetFoundFromSwapiEvent event) {
        event.getMessage().getResults().stream().map(mapper::toDomain).forEach(planet -> {
            if(!checkIfPlanetExistsWithName(planet.getName())) repository.save(planet);
        });
    }

    private boolean checkIfPlanetExistsWithName(String name){
        return repository.existsByName(name);
    }
}
