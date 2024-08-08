package com.example.pokemon.pokemon;

import com.example.pokemon.api.pokemon.dto.PokemonResponse;
import com.example.pokemon.domain.pokemon.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPokemonNoExceptionFound() {
        Pokemon pokemon = Pokemon.builder()
                .id(1L)
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokemon")
                .type(List.of(PokemonType.ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

        Pokemon result = pokemonService.getPokemonNoException(1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Pikachu");
    }

    @Test
    void testGetPokemonNoExceptionNotFound() {
        when(pokemonRepository.findById(anyInt())).thenReturn(Optional.empty());

        Pokemon result = pokemonService.getPokemonNoException(1);

        assertThat(result).isNull();
    }

    @Test
    void testGetPokemonFound() {
        Pokemon pokemon = Pokemon.builder()
                .id(1L)
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokemon")
                .type(List.of(PokemonType.ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();
        PokemonResponse pokemonResponse = PokemonResponse.of(pokemon);

        when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

        PokemonResponse result = pokemonService.getPokemon(1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Pikachu");
    }

    @Test
    void testGetPokemonNotFound() {
        when(pokemonRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pokemonService.getPokemon(1))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Pokemon not found with the given ID.");
    }

    @Test
    void testCreatePokemon() {
        Pokemon pokemon = Pokemon.builder()
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokemon")
                .type(List.of(PokemonType.ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();

        Pokemon savedPokemon = Pokemon.builder()
                .id(1L)
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokemon")
                .type(List.of(PokemonType.ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();

        when(pokemonRepository.save(pokemon)).thenReturn(savedPokemon);

        Pokemon result = pokemonService.createPokemon(pokemon);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Pikachu");
    }

    @Test
    void testGetPokemonCollectionFound() {
        Pokemon pokemon1 = Pokemon.builder()
                .id(1L)
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokemon")
                .type(List.of(PokemonType.ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();

        Pokemon pokemon2 = Pokemon.builder()
                .id(2L)
                .nationalNo(4)
                .name("Charmander")
                .species("Lizard Pokemon")
                .type(List.of(PokemonType.FIRE))
                .weight(8.5)
                .height(0.6)
                .build();

        PokemonCollection pokemonCollection = new PokemonCollection();
        pokemonCollection.setPokemonIds(Arrays.asList(1, 2));

        when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon1));
        when(pokemonRepository.findById(2)).thenReturn(Optional.of(pokemon2));

        List<Pokemon> result = pokemonService.getPokemonCollection(pokemonCollection);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Pokemon::getId).containsExactlyInAnyOrder(1L, 2L);
        assertThat(result).extracting(Pokemon::getName).containsExactlyInAnyOrder("Pikachu", "Charmander");
    }

    @Test
    void testGetPokemonCollectionNotFound() {
        PokemonCollection pokemonCollection = new PokemonCollection();
        pokemonCollection.setPokemonIds(Arrays.asList(1, 2));

        when(pokemonRepository.findById(1)).thenReturn(Optional.empty());
        when(pokemonRepository.findById(2)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pokemonService.getPokemonCollection(pokemonCollection))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Pokemon not found with the given ID.");
    }
}
