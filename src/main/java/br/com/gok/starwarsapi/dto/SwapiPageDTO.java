package br.com.gok.starwarsapi.dto;

import lombok.Value;

import java.util.List;

@Value
public class SwapiPageDTO {
    public int count;
    public String next;
    public String previous;
    public List<SwapiPlanetDTO> results;
}
