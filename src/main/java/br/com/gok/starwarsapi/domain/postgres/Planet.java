package br.com.gok.starwarsapi.domain.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Planet extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String climate;
    private String terrain;
    private Long population;
    private Integer appearancesInMovies;

    public Planet(Long id) {
        this.id = id;
    }
}
