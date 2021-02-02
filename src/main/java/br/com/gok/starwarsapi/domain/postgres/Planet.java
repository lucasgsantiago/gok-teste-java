package br.com.gok.starwarsapi.domain.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints=
    @UniqueConstraint(columnNames={"name"},name = "name_unique"))
public class Planet extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The planet name cannot be empty")
    private String name;
    private String climate;
    private String terrain;
    private String population;
    private Integer appearancesInMovies;
}
