package br.com.gok.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
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
