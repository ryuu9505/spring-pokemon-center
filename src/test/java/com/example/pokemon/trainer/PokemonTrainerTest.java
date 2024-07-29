package com.example.pokemon.trainer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PokemonTrainerTest {

    @Test
    void testPokemonTrainerBuilder() {
        PokemonTrainer trainer = PokemonTrainer.builder()
                .id(1L)
                .name("Ash")
                .age((short) 10)
                .gender(GenderType.MALE)
                .build();

        assertThat(trainer.getId()).isEqualTo(1L);
        assertThat(trainer.getName()).isEqualTo("Ash");
        assertThat(trainer.getAge()).isEqualTo((short) 10);
        assertThat(trainer.getGender()).isEqualTo(GenderType.MALE);
    }

    @Test
    void testNoArgsConstructor() {
        PokemonTrainer trainer = new PokemonTrainer();
        assertThat(trainer).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        PokemonTrainer trainer = new PokemonTrainer(1L, "Misty", (short) 12, GenderType.FEMALE);
        assertThat(trainer.getId()).isEqualTo(1L);
        assertThat(trainer.getName()).isEqualTo("Misty");
        assertThat(trainer.getAge()).isEqualTo((short) 12);
        assertThat(trainer.getGender()).isEqualTo(GenderType.FEMALE);
    }

    @Disabled // before todo set up validation
    @Test
    void testNotNullName() {
        assertThrows(NullPointerException.class, () -> {
            PokemonTrainer.builder()
                    .id(1L)
                    .age((short) 10)
                    .gender(GenderType.MALE)
                    .build();
        });
    }
}
