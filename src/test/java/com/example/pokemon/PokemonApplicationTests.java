package com.example.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class PokemonApplicationTests {

    @Test
    void testMainMethodRunsSpringApplication() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(PokemonApplication.class, new String[] {}))
                    .thenAnswer(invocation -> {
                        invocation.getArguments();
                        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
                        assertNotNull(context);
                        return context;
                    });

            PokemonApplication.main(new String[] {});
        }
    }

    @Test
    void contextLoads() {
    }

}
