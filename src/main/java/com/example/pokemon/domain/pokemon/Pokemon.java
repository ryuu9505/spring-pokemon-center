package com.example.pokemon.domain.pokemon;

import com.example.pokemon.domain.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pokemon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "national_no", unique = true)
    @NotNull
    private Integer nationalNo;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "species")
    private String species;

    @ElementCollection(targetClass = PokemonType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "pokemon_id"))
    @Column(name = "type")
    private List<PokemonType> type;

    @Column(name = "weight")
    @Max(1_000_000)
    @Min(0)
    private Double weight;

    @Column(name = "height")
    @Max(1_000_000)
    @Min(0)
    private Double height;
}
