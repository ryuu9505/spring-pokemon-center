package com.example.pokemon.pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.pokemon.pokemon.PokemonType.ELECTRIC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPokemonWithValidInput() throws Exception {
        Pokemon pokemon = Pokemon.builder()
                .nationalNo(25)
                .name("Pikachu")
                .species("Mouse Pokémon")
                .type(List.of(ELECTRIC))
                .weight(6.0)
                .height(0.4)
                .build();

        String pokemonJson = objectMapper.writeValueAsString(pokemon);
        mockMvc.perform(post("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createPokemonWithInvalidInput() throws Exception {
        String pokemonJson = "{ " +
                "\"national_no\": \"25\", " +
                "\"name\": \"Pikachu\", " +
                "\"species\": \"Mouse Pokémon\", " +
                "\"type\": [\"ELECTRIC\"], " +
                "\"weight\": \"aaa\", " + // wrong value of type
                "\"height\": 0.4 " +
                "}";

        mockMvc.perform(post("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonJson))
                .andExpect(status().is4xxClientError());
    }
}