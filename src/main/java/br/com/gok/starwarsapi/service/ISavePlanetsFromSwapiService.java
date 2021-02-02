package br.com.gok.starwarsapi.service;

import br.com.gok.starwarsapi.dto.SwapiPageDTO;

public interface ISavePlanetsFromSwapiService {
    void execute(SwapiPageDTO page);
}
