package app.cinema.demo.controller;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnaliseController.class)
class AnaliseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    @Test
    void testListarAnalises() throws Exception {
        // Given
        Filme filme = new Filme(1L, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Analise analise1 = new Analise(1L, filme, "Análise 1", 4);
        Analise analise2 = new Analise(2L, filme, "Análise 2", 5);
        when(filmeService.listarAnalises()).thenReturn(Arrays.asList(analise1, analise2));

        // When & Then
        mockMvc.perform(get("/analises"))
                .andExpect(status().isOk())
                .andExpect(view().name("analises/lista"))
                .andExpect(model().attributeExists("analises"));
    }
}