package br.com.gok.starwarsapi.util;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanetMapper {
    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    default Planet toDomain(SwapiPlanetDTO dto) {
        if ( dto == null ) {
            return null;
        }
        return Planet.builder()
                .name(dto.getName())
                .climate(dto.getClimate())
                .terrain(dto.getTerrain())
                .population(dto.getPopulation())
                .appearancesInMovies(dto.getFilms().size())
                .build();
    }

    PlanetDTO toPresenter(Planet model);
    @IterableMapping(elementTargetType = PlanetDTO.class)
    List<PlanetDTO> toPresenter(List<Planet> list);
}
