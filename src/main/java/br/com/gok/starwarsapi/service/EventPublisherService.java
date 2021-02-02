package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.service.event.PlanetFoundFromSwapiEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisherService {

    private final ApplicationEventPublisher applicationEventPublisher;

    void publishPlanetFoundFromSwapiEvent(final SwapiPageDTO message) {
        System.out.println("Publishing custom event. ");
        PlanetFoundFromSwapiEvent event = new PlanetFoundFromSwapiEvent(this, message);
        applicationEventPublisher.publishEvent(event);
    }
}
