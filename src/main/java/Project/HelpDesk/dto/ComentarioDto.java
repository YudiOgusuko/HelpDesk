package Project.HelpDesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ComentarioDto(
        @NotBlank String texto,
        @NotNull LocalDate dataCriacao
){
}
