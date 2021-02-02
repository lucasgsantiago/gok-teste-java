package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "API Pública Star Wars ", description = "Consulta de planetas na api pública de Star Wars")
public interface IPlanetSwapiResourceDoc {
    @Operation(summary = "Retorna uma lista paginada de Planetas da api pública")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Planetas da api pública"),
    })
    PageResponse<SwapiPlanetDTO> get(@ParameterObject Pageable pageable);

    @Operation(summary = "Retorna um Planeta a partir de um Nome válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Planeta não encontrado.")
    })
    SwapiPlanetDTO findByName(@RequestParam("name") String name);

}
