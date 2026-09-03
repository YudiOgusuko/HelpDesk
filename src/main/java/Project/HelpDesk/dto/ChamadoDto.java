package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Prioridade;
import Project.HelpDesk.enums.Status;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@JsonPropertyOrder({
        "idUsuario",
        "titulo",
        "descricao",
        "prioridade",
        "status",
        "idCategoria"
})

@Builder
public record ChamadoDto(@NotNull Long idUsuario,
                         @NotBlank String titulo,
                         @NotBlank String descricao,
                         @NotNull(message = "A prioridade não pode ser NULL.") Prioridade prioridade,
                         @NotNull(message = "O status não pode ser NULL") Status status,
                         @NotNull Long idCategoria
                        ){
}
