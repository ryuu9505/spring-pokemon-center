package com.example.pokemon.api.pokemon.dto;

import com.example.pokemon.domain.pokemon.Pokemon;
import com.example.pokemon.domain.pokemon.PokemonType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PokemonResponse {

    private Long id;
    private Integer nationalNo;
    private String name;
    private String species;
    private List<PokemonType> type;
    private Double weight;
    private Double height;

    @Builder
    private PokemonResponse(Long id, Integer nationalNo, String name, String species, List<PokemonType> type, Double weight, Double height) {
        this.id = id;
        this.nationalNo = nationalNo;
        this.name = name;
        this.species = species;
        this.type = type;
        this.weight = weight;
        this.height = height;
    }

    public static PokemonResponse of(Pokemon pokemon) {
        return PokemonResponse.builder()
                .id(pokemon.getId())
                .nationalNo(pokemon.getNationalNo())
                .name(pokemon.getName())
                .species(pokemon.getSpecies())
                .type(pokemon.getType())
                .weight(pokemon.getWeight())
                .height(pokemon.getHeight())
                .build();
    }
}
