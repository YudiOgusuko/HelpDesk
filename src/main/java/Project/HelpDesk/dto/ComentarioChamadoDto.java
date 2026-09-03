package Project.HelpDesk.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@JsonPropertyOrder({
        "texto",
        "chamado",
        "user"
})

@Builder
public record ComentarioChamadoDto(@NotBlank String texto,
                                   @NotNull Long chamado,
                                   @NotNull Long user
                                    ) implements ComentarioBaseDto {
}
