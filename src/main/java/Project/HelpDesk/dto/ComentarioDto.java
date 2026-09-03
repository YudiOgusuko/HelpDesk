package Project.HelpDesk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ComentarioDto(@NotNull String texto,
                            LocalDate dataCriacao) {
}
