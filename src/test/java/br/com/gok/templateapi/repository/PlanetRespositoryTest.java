package br.com.gok.templateapi.repository;

import br.com.gok.templateapi.domain.postgres.IPlanetRepository;
import br.com.gok.templateapi.domain.postgres.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class PlanetRespositoryTest
{
    @Autowired
    private IPlanetRepository repository;

    @Test
    void saveTest(){
        Planet planetToSave = new Planet().builder().name("Tatooine").climate("arid").population(200000L).terrain("desert").appearancesInMovies(5).build();
        Planet savededPlanet = repository.save(planetToSave);
        assertThat(savededPlanet,is(notNullValue()));
    }
}
