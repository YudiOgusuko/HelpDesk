package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Prioridade;
import Project.HelpDesk.enums.Status;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@JsonPropertyOrder({
        "idUsuario",
        "titulo",
        "descricao",
        "prioridade",
        "status",
        "idCategoria"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChamadoDto {

    @NotNull
    private Long idUsuario;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull(message = "A prioridade não pode ser NULL.")
    private Prioridade prioridade;

    @NotNull(message = "O status não pode ser NULL")
    private Status status;

    @NotNull
    private Long idCategoria;

}
