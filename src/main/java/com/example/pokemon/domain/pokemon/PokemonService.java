package com.example.pokemon.domain.pokemon;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public Pokemon getPokemonNoException(Integer pokemonId) {
        return pokemonRepository.findById(pokemonId).orElse(null);
    }

    public Pokemon getPokemon(Integer pokemonId) {
        return pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon not found with the given ID.")); // todo exception handling in global adviser
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> getPokemonCollection(PokemonCollection pokemonCollection) {
        List<Pokemon> pokemons = new ArrayList<>();

        for (Integer pokemonId : pokemonCollection.getPokemonIds()) {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new EntityNotFoundException("Pokemon not found with the given ID.")); // todo exception handling in global adviser
            pokemons.add(pokemon);
        }

        return pokemons;
    }
}
