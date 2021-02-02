package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.util.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "API Planetas Star Wars ", description = "Consulta de planetas da série Star Wars")
public interface IPlanetResourceDoc {
    @Operation(summary = "Retorna uma lista paginada de Planetas do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Planetas registrados no sistema"),
    })
    PageResponse<PlanetDTO> get(@ParameterObject Pageable pageable);

    @Operation(summary = "Retorna um Planeta a partir de um Id válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Planeta não encontrado.")
    })
    PlanetDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "Retorna um Planeta a partir de um Nome válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Planeta não encontrado.")
    })
    PlanetDTO findByName(@RequestParam("name") String name);

    @Operation(summary = "Retorna um Planeta a partir do filtro 'populacao'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Planeta não encontrado.")
    })
    PageResponse<PlanetDTO> filterByPopulation(@RequestParam("query") String query,@ParameterObject Pageable pageable);

    @Operation(summary = "Deleta Planeta encontrado a partir de um Id válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Planeta deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Planeta não encontrado.")
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
