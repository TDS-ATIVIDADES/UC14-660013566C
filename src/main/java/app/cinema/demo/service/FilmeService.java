package app.cinema.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;

@Service
public class FilmeService {
    private final List<Filme> filmes = new ArrayList<>();
    private final AtomicLong filmeIdCounter = new AtomicLong(1);
    private final AtomicLong analiseIdCounter = new AtomicLong(1);

    public List<Filme> listarFilmes() {
        return new ArrayList<>(filmes);
    }

    public List<Analise> listarAnalises() {
        return filmes.stream()
                .filter(f -> f.getAnalises() != null)
                .flatMap(f -> f.getAnalises().stream())
                .collect(Collectors.toList());
    }

    public Optional<Filme> buscarFilmePorId(Long id) {
        return filmes.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    public Filme salvarFilme(Filme filme) {
        if (filme.getId() == null) {
            filme.setId(filmeIdCounter.getAndIncrement());
        }
        filmes.add(filme);
        return filme;
    }

    public void adicionarAnalise(Long filmeId, Analise analise) {
        if (analise.getNota() < 1 || analise.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }
        Optional<Filme> filmeOpt = buscarFilmePorId(filmeId);
        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            analise.setId(analiseIdCounter.getAndIncrement());
            analise.setFilme(filme);
            if (filme.getAnalises() == null) {
                filme.setAnalises(new ArrayList<>());
            }
            filme.getAnalises().add(analise);
        }
    }
}