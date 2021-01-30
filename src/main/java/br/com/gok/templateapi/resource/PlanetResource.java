package br.com.gok.templateapi.resource;

import br.com.gok.templateapi.dto.SwapiPlanetDTO;
import br.com.gok.templateapi.service.ISWAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/template/v1/planets")
@RestController
public class PlanetResource {

	@Autowired
	private ISWAPIService swapiService;

	@GetMapping
	public SwapiPlanetDTO get() {
		return swapiService.getPlanets();
//		return swapiService.getPlanetByName("Tatooine");
	}
}