package app.cinema.demo.service;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        filmeService = new FilmeService();
    }

    @Test
    void testListarFilmes() {
        // Given
        Filme filme1 = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme filme2 = new Filme(null, "Filme 2", "Descrição 2", "Gênero 2", 2021);
        filmeService.salvarFilme(filme1);
        filmeService.salvarFilme(filme2);

        // When
        List<Filme> filmes = filmeService.listarFilmes();

        // Then
        assertEquals(2, filmes.size());
        assertEquals("Filme 1", filmes.get(0).getTitulo());
        assertEquals("Filme 2", filmes.get(1).getTitulo());
    }

    @Test
    void testBuscarFilmePorId() {
        // Given
        Filme filme = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme saved = filmeService.salvarFilme(filme);

        // When
        Optional<Filme> found = filmeService.buscarFilmePorId(saved.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("Filme 1", found.get().getTitulo());
    }

    @Test
    void testBuscarFilmePorIdNotFound() {
        // When
        Optional<Filme> found = filmeService.buscarFilmePorId(999L);

        // Then
        assertFalse(found.isPresent());
    }

    @Test
    void testSalvarFilme() {
        // Given
        Filme filme = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);

        // When
        Filme saved = filmeService.salvarFilme(filme);

        // Then
        assertNotNull(saved.getId());
        assertEquals("Filme 1", saved.getTitulo());
    }

    @Test
    void testAdicionarAnalise() {
        // Given
        Filme filme = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme saved = filmeService.salvarFilme(filme);
        Analise analise = new Analise(null, saved, "Análise boa", 4);

        // When
        filmeService.adicionarAnalise(saved.getId(), analise);

        // Then
        Optional<Filme> found = filmeService.buscarFilmePorId(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(1, found.get().getAnalises().size());
        assertEquals("Análise boa", found.get().getAnalises().get(0).getAnalise());
    }

    @Test
    void testAdicionarAnaliseNotaInvalidaBaixa() {
        // Given
        Filme filme = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme saved = filmeService.salvarFilme(filme);
        Analise analise = new Analise(null, saved, "Análise boa", 0);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> filmeService.adicionarAnalise(saved.getId(), analise));
    }

    @Test
    void testAdicionarAnaliseNotaInvalidaAlta() {
        // Given
        Filme filme = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme saved = filmeService.salvarFilme(filme);
        Analise analise = new Analise(null, saved, "Análise boa", 6);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> filmeService.adicionarAnalise(saved.getId(), analise));
    }

    @Test
    void testListarAnalises() {
        // Given
        Filme filme1 = new Filme(null, "Filme 1", "Descrição 1", "Gênero 1", 2020);
        Filme filme2 = new Filme(null, "Filme 2", "Descrição 2", "Gênero 2", 2021);
        filmeService.salvarFilme(filme1);
        filmeService.salvarFilme(filme2);
        Analise analise1 = new Analise(null, filme1, "Análise 1", 4);
        Analise analise2 = new Analise(null, filme2, "Análise 2", 5);
        filmeService.adicionarAnalise(filme1.getId(), analise1);
        filmeService.adicionarAnalise(filme2.getId(), analise2);

        // When
        List<Analise> analises = filmeService.listarAnalises();

        // Then
        assertEquals(2, analises.size());
        assertEquals("Análise 1", analises.get(0).getAnalise());
        assertEquals("Análise 2", analises.get(1).getAnalise());
    }
}