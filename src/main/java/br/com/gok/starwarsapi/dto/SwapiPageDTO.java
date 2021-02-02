package br.com.gok.starwarsapi.dto;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class SwapiPageDTO {
    private int count;
    private String next;
    private String previous;
    private final List<SwapiPlanetDTO> results;
}
