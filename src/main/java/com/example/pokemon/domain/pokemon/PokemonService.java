package com.example.pokemon.domain.pokemon;

import com.example.pokemon.api.pokemon.dto.PokemonResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public Pokemon getPokemonNoException(Integer pokemonId) {
        return pokemonRepository.findById(pokemonId).orElse(null);
    }

    public PokemonResponse getPokemon(Integer pokemonId) {
        Pokemon pokemon =  pokemonRepository.findById(pokemonId).orElseThrow(() -> new EntityNotFoundException("Pokemon not found with the given ID.")); // todo exception handling in global adviser
        return PokemonResponse.of(pokemon);
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
