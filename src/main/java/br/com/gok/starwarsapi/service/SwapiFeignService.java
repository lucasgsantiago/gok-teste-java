package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.exception.NotFoundException;
import br.com.gok.starwarsapi.service.client.StarWarsPublicAPIClient;
import br.com.gok.starwarsapi.util.Constants;
import br.com.gok.starwarsapi.util.PageResponse;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public PageResponse<SwapiPlanetDTO> getPlanets(Pageable pageable) {
        int swapiPageNumber = getPageNumber(pageable.getPageNumber());
        SwapiPageDTO page = swapiClient.getPlanets(swapiPageNumber);
        eventPublisher.publishPlanetFoundFromSwapiEvent(page);
        return new PageResponse<>(
                pageable.getPageSize(),
                page.getCount()/pageable.getPageSize(),
                swapiPageNumber,
                page.getCount(),
                page.getResults());
    }

    @Override
    public SwapiPlanetDTO findPlanetByName(String name) {
        SwapiPageDTO page = swapiClient.findPlanetByName(name);
        if(page.getResults().isEmpty()) {
            throw new NotFoundException(Constants.PLANET_NOT_FOUND);
        }
        eventPublisher.publishPlanetFoundFromSwapiEvent(page);
        return page.getResults().get(0);
    }

    private int getPageNumber(int pageNumberGiven){
        return pageNumberGiven == 0 ? pageNumberGiven+1 : pageNumberGiven;
    }

}
