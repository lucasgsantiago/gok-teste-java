package br.com.gok.starwarsapi.util;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanetMapper {
    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    Planet toDomain(SwapiPlanetDTO dto);
    PlanetDTO toPresenter(Planet model);
    @IterableMapping(elementTargetType = PlanetDTO.class)
    List<PlanetDTO> toPresenter(List<Planet> list);
}
