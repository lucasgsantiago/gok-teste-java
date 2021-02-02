package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.IPlanetRepository;
import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.exception.BadRequestException;
import br.com.gok.starwarsapi.service.event.PlanetFoundFromSwapiEvent;
import br.com.gok.starwarsapi.util.Constants;
import br.com.gok.starwarsapi.util.PlanetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePlanetsFromSwapiService implements ISavePlanetsFromSwapiService {
    private final IPlanetRepository repository;
    private PlanetMapper mapper = PlanetMapper.INSTANCE;

    @Override
    public void execute(SwapiPageDTO page) {
        page.getResults().stream().map(mapper::toDomain).forEach(planet -> {
            checkIfPlanetExistsWithName(planet.getName());
            repository.save(planet);
        });
    }

    @EventListener
    public void handler(PlanetFoundFromSwapiEvent event) {
        event.getMessage().getResults().stream().map(mapper::toDomain).forEach(planet -> {
            checkIfPlanetExistsWithName(planet.getName());
            repository.save(planet);
        });
    }

    private void checkIfPlanetExistsWithName(String name){
        if(repository.findByName(name).isPresent()) new BadRequestException(Constants.PLANET_ALREADY_EXISTS);
    }
}
