package app.cinema.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.repository.AnaliseRepository;
import app.cinema.demo.repository.FilmeRepository;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final AnaliseRepository analiseRepository;

    public FilmeService(FilmeRepository filmeRepository, AnaliseRepository analiseRepository) {
        this.filmeRepository = filmeRepository;
        this.analiseRepository = analiseRepository;
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public List<Analise> listarAnalises() {
        return analiseRepository.findAll();
    }

    public Optional<Filme> buscarFilmePorId(Long id) {
        return filmeRepository.findById(id);
    }

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void adicionarAnalise(Long filmeId, Analise analise) {
        if (analise.getNota() < 1 || analise.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }
        Optional<Filme> filmeOpt = filmeRepository.findById(filmeId);
        if (filmeOpt.isPresent()) {
            analise.setFilme(filmeOpt.get());
            analiseRepository.save(analise);
        } else {
            throw new IllegalArgumentException("Filme não encontrado");
        }
    }

    public void deletarFilme(Long id) {
        filmeRepository.deleteById(id);
    }

    public void deletarAnalise(Long id) {
        analiseRepository.deleteById(id);
    }
}