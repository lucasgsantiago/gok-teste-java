package br.com.gok.templateapi.resource;

import io.swagger.v3.core.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/template/v1")
@RestController
public class TemplateResource {

    @GetMapping("/healtcheck")
    public String templateHealtCheck() {

        return Json.pretty(true);
    }
}