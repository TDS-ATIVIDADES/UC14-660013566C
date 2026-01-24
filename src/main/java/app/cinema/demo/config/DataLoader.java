package app.cinema.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.cinema.demo.model.Analise;
import app.cinema.demo.model.Filme;
import app.cinema.demo.service.FilmeService;

@Component
public class DataLoader implements CommandLineRunner {

    private final FilmeService filmeService;

    public DataLoader(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Filme 1
        Filme filme1 = new Filme(null, "Inception", "Um ladrão que rouba segredos corporativos através do uso de tecnologia de compartilhamento de sonhos.", "Ficção Científica", 2010);
        filmeService.salvarFilme(filme1);
        Analise analise1 = new Analise(null, filme1, "Excelente filme, muito complexo e bem dirigido.", 4);
        filmeService.adicionarAnalise(filme1.getId(), analise1);

        // Filme 2
        Filme filme2 = new Filme(null, "The Shawshank Redemption", "Dois homens presos se encontram em uma prisão brutal, onde um ensina ao outro a esperança.", "Drama", 1994);
        filmeService.salvarFilme(filme2);
        Analise analise2 = new Analise(null, filme2, "Um clássico, história emocionante.", 5);
        filmeService.adicionarAnalise(filme2.getId(), analise2);

        // Filme 3
        Filme filme3 = new Filme(null, "Pulp Fiction", "As vidas de dois assassinos de aluguel, um boxeador, uma garçonete e um casal de bandidos se entrelaçam em quatro histórias de violência e redenção.", "Crime", 1994);
        filmeService.salvarFilme(filme3);
        Analise analise3 = new Analise(null, filme3, "Estilo único de Tarantino, muito bom.", 3);
        filmeService.adicionarAnalise(filme3.getId(), analise3);
    }
}