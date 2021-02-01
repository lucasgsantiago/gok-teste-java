package br.com.gok.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PlanetDTO {
    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private String population;
    private Integer appearancesInMovies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<String> films;
}
