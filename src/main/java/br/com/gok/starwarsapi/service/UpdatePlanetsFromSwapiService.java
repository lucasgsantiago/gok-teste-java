package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class UpdatePlanetsFromSwapiService implements IUpdatePlanetsFromSwapiService {
    private final ISWAPIService service;

    @Override
    public Page<Planet> execute(Pageable pageable) {
        //service.getPlanets()
        return null;
    }
}
