package br.com.gok.starwarsapi.service.event;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import org.springframework.context.ApplicationEvent;

public class PlanetFoundFromSwapiEvent extends ApplicationEvent {
    private SwapiPageDTO message;

    public PlanetFoundFromSwapiEvent(Object source, SwapiPageDTO message) {
        super(source);
        this.message = message;
    }

    public SwapiPageDTO getMessage() {
        return message;
    }
}
