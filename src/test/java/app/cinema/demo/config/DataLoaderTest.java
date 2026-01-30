package app.cinema.demo.config;

import app.cinema.demo.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class DataLoaderTest {

    @Autowired
    private FilmeService filmeService;

    @Test
    void testDataLoader() {
        // When
        var filmes = filmeService.listarFilmes();
        var analises = filmeService.listarAnalises();

        // Then
        assertEquals(3, filmes.size());
        assertEquals(3, analises.size());
        assertEquals("Inception", filmes.get(0).getTitulo());
        assertEquals("The Shawshank Redemption", filmes.get(1).getTitulo());
        assertEquals("Pulp Fiction", filmes.get(2).getTitulo());
    }
}