package app.cinema.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 255, message = "Título deve ter entre 1 e 255 caracteres")
    private String titulo;

    @Size(max = 1000, message = "Sinopse deve ter no máximo 1000 caracteres")
    private String sinopse;

    @Size(max = 100, message = "Gênero deve ter no máximo 100 caracteres")
    private String genero;

    @NotNull(message = "Ano de lançamento é obrigatório")
    @Min(value = 1900, message = "Ano de lançamento deve ser pelo menos 1900")
    @Max(value = 2100, message = "Ano de lançamento deve ser no máximo 2100")
    private Integer anoLancamento;
}