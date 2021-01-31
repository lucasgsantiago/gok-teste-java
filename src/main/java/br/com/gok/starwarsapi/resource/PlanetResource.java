package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.service.IPlanetService;
import br.com.gok.starwarsapi.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/starwars/v1/planets")
@RestController
@RequiredArgsConstructor
public class PlanetResource {

	private final IPlanetService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageResponse<PlanetDTO> get(@PageableDefault Pageable pageable){
		return service.listAll(pageable);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		service.remove(id);
	}
}