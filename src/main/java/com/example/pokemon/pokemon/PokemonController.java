package com.example.pokemon.pokemon;

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
    public Pokemon getPokemon(@PathVariable("pokemonId") Integer pokemonId) {
        return pokemonService.getPokemon(pokemonId);
    }

    @GetMapping(value = "/collection")
    public List<Pokemon> getPokemonValid(@RequestBody PokemonCollection pokemonCollection) {
        return pokemonService.getPokemonCollection(pokemonCollection);
    }

    @GetMapping(value = "/params")
    public Pokemon getPokemonRequestParam(@RequestParam("pokemonId") Integer pokemonId)  {
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
