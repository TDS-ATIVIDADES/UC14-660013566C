package app.cinema.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseDTO {

    @NotNull(message = "ID do filme é obrigatório")
    private Long filmeId;

    @Size(max = 2000, message = "Análise deve ter no máximo 2000 caracteres")
    private String analise;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota deve ser pelo menos 1")
    @Max(value = 5, message = "Nota deve ser no máximo 5")
    private Integer nota;
}