package app.cinema.demo.controller.api;

import app.cinema.demo.model.Analise;
import app.cinema.demo.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
@Tag(name = "Análises", description = "API para gerenciamento de análises de filmes")
public class AnaliseRestController {

    private final FilmeService filmeService;

    public AnaliseRestController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as análises")
    public ResponseEntity<List<Analise>> listarAnalises() {
        List<Analise> analises = filmeService.listarAnalises();
        return ResponseEntity.ok(analises);
    }

    @PostMapping
    @Operation(summary = "Criar nova análise")
    public ResponseEntity<Void> criarAnalise(@RequestBody Analise analise) {
        try {
            filmeService.adicionarAnalise(analise.getFilme().getId(), analise);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar análise")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Long id) {
        try {
            filmeService.deletarAnalise(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}