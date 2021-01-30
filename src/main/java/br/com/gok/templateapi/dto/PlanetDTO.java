package br.com.gok.templateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
@JsonInclude(value = Include.NON_NULL)
public class PlanetDTO {
    public String name;
    public String climate;
    public String terrain;
    public String population;
    public Set<String> films;
}
