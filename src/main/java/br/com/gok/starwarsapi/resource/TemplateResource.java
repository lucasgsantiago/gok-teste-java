package br.com.gok.starwarsapi.resource;

import br.com.gok.starwarsapi.dto.TemplateDTO;
import io.swagger.v3.core.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/template/v1")
@RestController
public class TemplateResource {

    @GetMapping("/healtcheck")
    public String templateHealtCheck() {
        TemplateDTO template = new TemplateDTO(true);
        return Json.pretty(template);
    }
}