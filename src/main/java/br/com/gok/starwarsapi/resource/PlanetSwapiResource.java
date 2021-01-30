package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.SwapiPlanetDTO;
import br.com.gok.starwarsapi.service.ISWAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/swapi/v1/planets")
@RestController
public class PlanetSwapiResource {

	@Autowired
	private ISWAPIService swapiService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public SwapiPlanetDTO get() {
		return swapiService.getPlanets();
	}
}