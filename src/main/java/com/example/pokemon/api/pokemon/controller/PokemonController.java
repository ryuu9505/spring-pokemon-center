package com.example.pokemon.api.pokemon.controller;

import com.example.pokemon.api.pokemon.dto.PokemonResponse;
import com.example.pokemon.domain.pokemon.Pokemon;
import com.example.pokemon.domain.pokemon.PokemonCollection;
import com.example.pokemon.domain.pokemon.PokemonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping(value = "/{pokemonId}")
    public PokemonResponse getPokemon(@PathVariable("pokemonId") Integer pokemonId) {
        return pokemonService.getPokemon(pokemonId);
    }

    @GetMapping(value = "/collection")
    public List<Pokemon> getPokemonValid(@RequestBody PokemonCollection pokemonCollection) {
        return pokemonService.getPokemonCollection(pokemonCollection);
    }

    @GetMapping(value = "/params")
    public PokemonResponse getPokemonRequestParam(@RequestParam("pokemonId") Integer pokemonId)  {
        return pokemonService.getPokemon(pokemonId);
    }

    @GetMapping(value = "/noexception/{pokemonId}")
    public Pokemon getPokemonNoException(@PathVariable("pokemonId") Integer pokemonId) {
        return pokemonService.getPokemonNoException(pokemonId);
    }

    @PostMapping
    public Pokemon createPokemon(@RequestBody @Valid Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }
}
