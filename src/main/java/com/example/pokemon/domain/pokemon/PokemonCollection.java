package com.example.pokemon.domain.pokemon;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class PokemonCollection {

    @NotNull
    @Size(min = 2)
    private List<Integer> pokemonIds;

    public void setPokemonIds(List<Integer> pokemonIds) {
        this.pokemonIds = pokemonIds;
    }
}
