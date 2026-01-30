package app.cinema.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cinema.demo.dto.AnaliseDTO;
import app.cinema.demo.dto.FilmeDTO;
import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.service.FilmeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "filmes/lista";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filmeDTO", new FilmeDTO());
        return "filmes/form";
    }

    @PostMapping
    public String salvarFilme(@Valid @ModelAttribute FilmeDTO filmeDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filmeDTO", filmeDTO);
            return "filmes/form";
        }
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setSinopse(filmeDTO.getSinopse());
        filme.setGenero(filmeDTO.getGenero());
        filme.setAnoLancamento(filmeDTO.getAnoLancamento());
        filmeService.salvarFilme(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Optional<Filme> filmeOpt = filmeService.buscarFilmePorId(id);
        if (filmeOpt.isPresent()) {
            model.addAttribute("filme", filmeOpt.get());
            return "filmes/detalhes";
        } else {
            return "redirect:/filmes";
        }
    }

    @GetMapping("/{id}/avaliar")
    public String avaliarFilme(@PathVariable Long id, Model model) {
        filmeService.buscarFilmePorId(id).ifPresent(filme -> model.addAttribute("filme", filme));
        model.addAttribute("analiseDTO", new AnaliseDTO());
        return "filmes/avaliar";
    }

    @PostMapping("/{id}/avaliar")
    public String adicionarAnalise(@PathVariable Long id, @Valid @ModelAttribute AnaliseDTO analiseDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            filmeService.buscarFilmePorId(id).ifPresent(filme -> model.addAttribute("filme", filme));
            model.addAttribute("analiseDTO", analiseDTO);
            return "filmes/avaliar";
        }
        Optional<Filme> filmeOpt = filmeService.buscarFilmePorId(id);
        if (filmeOpt.isEmpty()) {
            throw new IllegalArgumentException("Filme não encontrado");
        }
        Analise analise = new Analise();
        analise.setAnalise(analiseDTO.getAnalise());
        analise.setNota(analiseDTO.getNota());
        filmeService.adicionarAnalise(id, analise);
        return "redirect:/filmes/" + id;
    }
}