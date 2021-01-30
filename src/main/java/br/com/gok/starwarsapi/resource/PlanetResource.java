package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.domain.postgres.Planet;
import br.com.gok.starwarsapi.service.IPlanetService;
import lombok.RequiredArgsConstructor;
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
	public List<Planet> get() {
		return service.listAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		service.remove(id);
	}
}