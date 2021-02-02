package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.service.IPlanetService;
import br.com.gok.starwarsapi.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/starwars/v1/planets")
@RestController
@RequiredArgsConstructor
public class PlanetResource implements IPlanetResourceDoc{

	private final IPlanetService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageResponse<PlanetDTO> get(@ParameterObject Pageable pageable){
		return service.listAll(pageable);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PlanetDTO findById(@PathVariable("id") Long id) {
		return this.service.findById(id);
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public PlanetDTO findByName(@RequestParam("name") String name) {
		return this.service.findByName(name);
	}

	@GetMapping("/filter")
	@ResponseStatus(HttpStatus.OK)
	public PageResponse<PlanetDTO> filterByPopulation(@RequestParam("query") String query,@ParameterObject Pageable pageable) {
		return this.service.filterByQuery(query,pageable);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}