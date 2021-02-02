package br.com.gok.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SwapiPlanetDTO {
    private String name;
    private String climate;
    private String terrain;
    private String population;
    private String surface_water;
    private String gravity;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private List<String> residents;
    private List<String> films;
    private String created;
    private String edited;
}
