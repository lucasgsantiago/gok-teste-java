package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.service.client.StarWarsPublicAPIClient;
import br.com.gok.starwarsapi.util.PageResponse;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwapiFeignService implements ISWAPIService {

    private static final String SWAPI_URL = "https://swapi.dev/api";
    private final EventPublisherService eventPublisher;

    private final StarWarsPublicAPIClient swapiClient = Feign.builder()
            .decoder(new GsonDecoder())
            .target(StarWarsPublicAPIClient.class, SWAPI_URL);

    @Override
    public PageResponse<SwapiPlanetDTO> getPlanets() {
        PageResponse<SwapiPlanetDTO> rersponse = null;
        SwapiPageDTO page = swapiClient.getPlanets();
        return new PageResponse<SwapiPlanetDTO>(10,page.getCount()/10,page.getCount(),page.getCount(),page.getResults());
    }

    @Override
    public SwapiPlanetDTO findPlanetByName(String name) {
        SwapiPageDTO page =  swapiClient.findPlanetByName(name);
        eventPublisher.publishPlanetFoundFromSwapiEvent(page);
        return page.getResults().get(0);
    }

}
