package app.cinema.demo.controller;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.repository.FilmeRepository;
import app.cinema.demo.service.FilmeService;
import app.cinema.demo.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnaliseController.class)
@AutoConfigureMockMvc(addFilters = false)
class AnaliseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void testListarAnalises() throws Exception {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        
        Analise analise1 = new Analise();
        analise1.setId(1L);
        analise1.setFilme(filme);
        analise1.setAnalise("Análise 1");
        analise1.setNota(4);
        
        Analise analise2 = new Analise();
        analise2.setId(2L);
        analise2.setFilme(filme);
        analise2.setAnalise("Análise 2");
        analise2.setNota(5);
        
        when(filmeService.listarAnalises()).thenReturn(Arrays.asList(analise1, analise2));

        // When & Then
        mockMvc.perform(get("/analises"))
                .andExpect(status().isOk())
                .andExpect(view().name("analises/lista"))
                .andExpect(model().attributeExists("analises"));
    }
}