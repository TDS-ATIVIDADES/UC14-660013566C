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
import org.springframework.http.MediaType;
import jakarta.servlet.ServletException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(FilmeController.class)
@AutoConfigureMockMvc(addFilters = false)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    @MockBean
    private FilmeRepository filmeRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void testListarFilmes() throws Exception {
        // Given
        Filme filme1 = new Filme();
        filme1.setId(1L);
        filme1.setTitulo("Filme 1");
        filme1.setSinopse("Descrição 1");
        filme1.setGenero("Gênero 1");
        filme1.setAnoLancamento(2020);
        
        Filme filme2 = new Filme();
        filme2.setId(2L);
        filme2.setTitulo("Filme 2");
        filme2.setSinopse("Descrição 2");
        filme2.setGenero("Gênero 2");
        filme2.setAnoLancamento(2021);
        
        when(filmeService.listarFilmes()).thenReturn(Arrays.asList(filme1, filme2));

        // When & Then
        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(view().name("filmes/lista"))
                .andExpect(model().attributeExists("filmes"));
    }

    @Test
    void testNovoFilme() throws Exception {
        // When & Then
        mockMvc.perform(get("/filmes/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("filmes/form"))
                .andExpect(model().attributeExists("filmeDTO"));
    }

    @Test
    void testSalvarFilme() throws Exception {
        // Given
        Filme filme = new Filme();
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        when(filmeService.salvarFilme(any(Filme.class))).thenReturn(filme);

        // When & Then
        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("titulo", "Filme 1")
                        .param("sinopse", "Descrição 1")
                        .param("genero", "Gênero 1")
                        .param("anoLancamento", "2020"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/filmes"));
    }

    @Test
    void testDetalhesFilme() throws Exception {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        when(filmeService.buscarFilmePorId(1L)).thenReturn(Optional.of(filme));

        // When & Then
        mockMvc.perform(get("/filmes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("filmes/detalhes"))
                .andExpect(model().attributeExists("filme"));
    }

    @Test
    void testDetalhesFilmeNaoEncontrado() throws Exception {
        // Given
        when(filmeService.buscarFilmePorId(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/filmes/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/filmes"));
    }

    @Test
    void testAvaliarFilme() throws Exception {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        when(filmeService.buscarFilmePorId(1L)).thenReturn(Optional.of(filme));

        // When & Then
        mockMvc.perform(get("/filmes/1/avaliar"))
                .andExpect(status().isOk())
                .andExpect(view().name("filmes/avaliar"))
                .andExpect(model().attributeExists("filme"))
                .andExpect(model().attributeExists("analiseDTO"));
    }

    @Test
    void testAdicionarAnalise() throws Exception {
        // Given
        Analise analise = new Analise();
        analise.setAnalise("Análise boa");
        analise.setNota(4);
        
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme Teste");
        filme.setSinopse("Descrição");
        filme.setGenero("Ação");
        filme.setAnoLancamento(2020);
        
        when(filmeService.buscarFilmePorId(1L)).thenReturn(Optional.of(filme));
        doNothing().when(filmeService).adicionarAnalise(eq(1L), any(Analise.class));

        // When & Then
        mockMvc.perform(post("/filmes/1/avaliar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("analise", "Análise boa")
                        .param("nota", "4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/filmes/1"));
    }

    @Test
    void testAdicionarAnaliseFilmeNaoEncontrado() throws Exception {
        // Given
        when(filmeService.buscarFilmePorId(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/filmes/999/avaliar")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("analise", "Análise boa")
                            .param("nota", "4"));
        });
    }
}