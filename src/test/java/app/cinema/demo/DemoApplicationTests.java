package app.cinema.demo;

import app.cinema.demo.repository.AnaliseRepository;
import app.cinema.demo.repository.FilmeRepository;
import app.cinema.demo.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class CinemaApplicationTests {

    @MockBean
    private FilmeRepository filmeRepository;

    @MockBean
    private AnaliseRepository analiseRepository;

    @MockBean
    private JwtUtil jwtUtil;

	@Test
	void contextLoads() {
	}
}
