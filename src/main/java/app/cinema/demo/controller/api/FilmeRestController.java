package app.cinema.demo.controller.api;

import app.cinema.demo.model.Filme;
import app.cinema.demo.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@Tag(name = "Filmes", description = "API para gerenciamento de filmes")
public class FilmeRestController {

    private final FilmeService filmeService;

    public FilmeRestController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os filmes")
    public ResponseEntity<List<Filme>> listarFilmes() {
        List<Filme> filmes = filmeService.listarFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID")
    public ResponseEntity<Filme> buscarFilme(@PathVariable Long id) {
        return filmeService.buscarFilmePorId(id)
                .map(filme -> ResponseEntity.ok(filme))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo filme")
    public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
        Filme novoFilme = filmeService.salvarFilme(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFilme);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar filme existente")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        if (!filmeService.buscarFilmePorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filme.setId(id);
        Filme filmeAtualizado = filmeService.salvarFilme(filme);
        return ResponseEntity.ok(filmeAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        if (!filmeService.buscarFilmePorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}