package app.cinema.demo.service;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.repository.AnaliseRepository;
import app.cinema.demo.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private AnaliseRepository analiseRepository;

    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        filmeService = new FilmeService(filmeRepository, analiseRepository);
    }

    @Test
    void testListarFilmes() {
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
        
        when(filmeRepository.findAll()).thenReturn(Arrays.asList(filme1, filme2));

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
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        // When
        Optional<Filme> found = filmeService.buscarFilmePorId(1L);

        // Then
        assertTrue(found.isPresent());
        assertEquals("Filme 1", found.get().getTitulo());
    }

    @Test
    void testBuscarFilmePorIdNotFound() {
        // Given
        when(filmeRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Filme> found = filmeService.buscarFilmePorId(999L);

        // Then
        assertFalse(found.isPresent());
    }

    @Test
    void testSalvarFilme() {
        // Given
        Filme filme = new Filme();
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        
        Filme savedFilme = new Filme();
        savedFilme.setId(1L);
        savedFilme.setTitulo("Filme 1");
        savedFilme.setSinopse("Descrição 1");
        savedFilme.setGenero("Gênero 1");
        savedFilme.setAnoLancamento(2020);
        
        when(filmeRepository.save(any(Filme.class))).thenReturn(savedFilme);

        // When
        Filme saved = filmeService.salvarFilme(filme);

        // Then
        assertNotNull(saved.getId());
        assertEquals("Filme 1", saved.getTitulo());
    }

    @Test
    void testAdicionarAnalise() {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        filme.setSinopse("Descrição 1");
        filme.setGenero("Gênero 1");
        filme.setAnoLancamento(2020);
        
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        
        Analise analise = new Analise();
        analise.setFilme(filme);
        analise.setAnalise("Análise boa");
        analise.setNota(4);

        // When
        filmeService.adicionarAnalise(1L, analise);

        // Then
        verify(analiseRepository).save(analise);
    }

    @Test
    void testAdicionarAnaliseNotaInvalidaBaixa() {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        
        Analise analise = new Analise();
        analise.setFilme(filme);
        analise.setAnalise("Análise boa");
        analise.setNota(0);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> filmeService.adicionarAnalise(1L, analise));
    }

    @Test
    void testAdicionarAnaliseNotaInvalidaAlta() {
        // Given
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Filme 1");
        
        Analise analise = new Analise();
        analise.setFilme(filme);
        analise.setAnalise("Análise boa");
        analise.setNota(6);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> filmeService.adicionarAnalise(1L, analise));
    }

    @Test
    void testAdicionarAnaliseFilmeNaoEncontrado() {
        // Given
        when(filmeRepository.findById(999L)).thenReturn(Optional.empty());
        
        Analise analise = new Analise();
        analise.setAnalise("Análise boa");
        analise.setNota(4);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> filmeService.adicionarAnalise(999L, analise));
    }

    @Test
    void testListarAnalises() {
        // Given
        Analise analise1 = new Analise();
        analise1.setId(1L);
        analise1.setAnalise("Análise 1");
        analise1.setNota(4);
        
        Analise analise2 = new Analise();
        analise2.setId(2L);
        analise2.setAnalise("Análise 2");
        analise2.setNota(5);
        
        when(analiseRepository.findAll()).thenReturn(Arrays.asList(analise1, analise2));

        // When
        List<Analise> analises = filmeService.listarAnalises();

        // Then
        assertEquals(2, analises.size());
        assertEquals("Análise 1", analises.get(0).getAnalise());
        assertEquals("Análise 2", analises.get(1).getAnalise());
    }

    @Test
    void testDeletarFilme() {
        // When
        filmeService.deletarFilme(1L);

        // Then
        verify(filmeRepository).deleteById(1L);
    }

    @Test
    void testDeletarAnalise() {
        // When
        filmeService.deletarAnalise(1L);

        // Then
        verify(analiseRepository).deleteById(1L);
    }
}