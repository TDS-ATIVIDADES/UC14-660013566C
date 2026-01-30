package app.cinema.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.service.FilmeService;

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
        model.addAttribute("filme", new Filme());
        return "filmes/form";
    }

    @PostMapping
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeService.salvarFilme(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        filmeService.buscarFilmePorId(id).ifPresent(filme -> model.addAttribute("filme", filme));
        return "filmes/detalhes";
    }

    @GetMapping("/{id}/avaliar")
    public String avaliarFilme(@PathVariable Long id, Model model) {
        filmeService.buscarFilmePorId(id).ifPresent(filme -> model.addAttribute("filme", filme));
        model.addAttribute("analise", new Analise());
        return "filmes/avaliar";
    }

    @PostMapping("/{id}/avaliar")
    public String adicionarAnalise(@PathVariable Long id, @ModelAttribute Analise analise) {
        filmeService.adicionarAnalise(id, analise);
        return "redirect:/filmes/" + id;
    }
}