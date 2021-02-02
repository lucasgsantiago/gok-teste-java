package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.PlanetDTO;
import br.com.gok.starwarsapi.dto.SwapiPageDTO;
import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.service.ISWAPIService;
import br.com.gok.starwarsapi.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/swapi/v1/planets")
@RestController
public class PlanetSwapiResource {

	@Autowired
	private ISWAPIService swapiService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageResponse<SwapiPlanetDTO> get() {
		return swapiService.getPlanets();
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public SwapiPlanetDTO findByName(@RequestParam("name") String name) {
		return this.swapiService.findPlanetByName(name);
	}
}