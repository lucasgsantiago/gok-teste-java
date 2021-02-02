package br.com.gok.starwarsapi;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;

public class PlanetFactory {

    public static Planet createPlanetToBeSaved(){
        return new Planet().builder().name("Dagobah").climate("murky").population("unknown").terrain("swamp, jungles").appearancesInMovies(5).build();
    }

    public static Planet createValidPlanet(){
        return new Planet().builder().id(1L).name("Tatooine").climate("arid").population("200000").terrain("desert").appearancesInMovies(3).build();
    }

    public static PlanetDTO createValidPlanetDTO(){
        return new PlanetDTO().builder().id(1L).name("Tatooine").climate("arid").population("200000").terrain("desert").appearancesInMovies(5).build();
    }

    public static SwapiPlanetDTO createValidSwapiPlanetDTO(){
        return new SwapiPlanetDTO().builder().name("Tatooine").climate("arid").population("200000").terrain("desert").build();
    }
}
