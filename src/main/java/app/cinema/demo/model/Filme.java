package app.cinema.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "filme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    private String genero;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Analise> analises;
}