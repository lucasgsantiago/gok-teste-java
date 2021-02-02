package br.com.gok.starwarsapi.repository;

import br.com.gok.starwarsapi.PlanetFactory;
import br.com.gok.starwarsapi.domain.postgres.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJpaTest
@DisplayName("Tests for Planet Repository")
class PlanetJPARepositoryTest {

    @Autowired
    PlanetJPARepository repository;

    @Test
    @DisplayName("Save persists Planet when successful")
    void save_PersistsPlanet_WhenSuccessful() {
        Planet planetToBeSaved = PlanetFactory.createPlanetToBeSaved();
        Planet savedPlanet = repository.save(planetToBeSaved);

        assertThat(savedPlanet).isNotNull();
        assertThat(savedPlanet.getId()).isNotNull();
        assertThat(savedPlanet.getName()).isEqualTo(planetToBeSaved.getName());
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
        Planet planet = new Planet();
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(planet))
                .withMessageContaining("The planet name cannot be empty");
    }

    @Test
    @DisplayName("Save throw DataIntegrityViolationException when name already exists")
    void save_ThrowsDataIntegrityViolationException_WhenNameAlreadyExists(){
        Planet planetToBeSaved = PlanetFactory.createPlanetToBeSaved();
        repository.save(planetToBeSaved);
        Planet planetTwo = PlanetFactory.createPlanetToBeSaved();
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> this.repository.save(planetTwo));
    }

    @Test
    @DisplayName("Delete Planet when successful")
    void delete_Planet_WhenSuccessful() {
        Planet planetToBeSaved = PlanetFactory.createPlanetToBeSaved();
        Planet savedPlanet = repository.save(planetToBeSaved);

        this.repository.delete(savedPlanet);

        Optional<Planet> animeOptional = this.repository.findById(savedPlanet.getId());
        assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Get All Planets when successful")
    void getAll() {
        Planet planetToBeSaved = PlanetFactory.createPlanetToBeSaved();
        Planet savedPlanet = repository.save(planetToBeSaved);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("name")));
        Page<Planet> pagePlanet = this.repository.getAll(pageable);

        assertThat(pagePlanet)
                .isNotEmpty()
                .contains(savedPlanet);
        assertThat(pagePlanet.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get a Planet with a given name when successful")
    void findByName() {
        Planet planetToBeSaved = PlanetFactory.createPlanetToBeSaved();
        Planet savedPlanet = repository.save(planetToBeSaved);

        String name = savedPlanet.getName();
        Optional<Planet> planet = this.repository.findByName(name);

        assertThat(planet)
                .isNotEmpty()
                .contains(savedPlanet);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no planet is found")
    void findByName_ReturnsEmpty_WhenAnimeIsNotFound(){
        Optional<Planet> planet = this.repository.findByName("abcdfghi");

        assertThat(planet).isEmpty();
    }

    @Test
    void findById() {
        Planet planetToBeSaved = new Planet().builder().name("Alderaan").climate("arid").population("200000").terrain("desert").appearancesInMovies(5).build();
        Planet savedPlanet = repository.save(planetToBeSaved);

        Optional<Planet> expectedPlanet = this.repository.findById(savedPlanet.getId());

        assertThat(expectedPlanet)
                .isNotEmpty()
                .contains(savedPlanet);
    }


    @Test
    void filterByQuery() {
    }

    @Test
    void existsByName() {
        Planet planetToBeSaved = new Planet().builder().name("Hoth").climate("arid").population("200000").terrain("desert").appearancesInMovies(5).build();
        Planet savedPlanet = repository.save(planetToBeSaved);

        String name = savedPlanet.getName();
        boolean exist = this.repository.existsByName(name);

        assertThat(exist).isTrue();
    }
}