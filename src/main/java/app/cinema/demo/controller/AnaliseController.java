package app.cinema.demo.controller;

import app.cinema.demo.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnaliseController {

    private final FilmeService filmeService;

    public AnaliseController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/analises")
    public String listarAnalises(Model model) {
        model.addAttribute("analises", filmeService.listarAnalises());
        return "analises/lista";
    }
}