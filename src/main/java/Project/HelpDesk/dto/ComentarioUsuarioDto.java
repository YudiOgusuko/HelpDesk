package Project.HelpDesk.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@JsonPropertyOrder({
        "texto",
        "chamado",
        "user"
})

public record ComentarioUsuarioDto(@NotBlank String texto,
                                   @NotNull Long user
                                    ) implements ComentarioBaseDto {
}
