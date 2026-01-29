package app.cinema.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "analise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id", nullable = false)
    @JsonBackReference
    private Filme filme;

    @Column(columnDefinition = "TEXT")
    private String analise;

    @Column(nullable = false)
    private Integer nota;
}