package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@JsonPropertyOrder({
        "nome",
        "email",
        "perfil"
})

@Builder
public record UsuarioDto(@NotBlank String nome,
                         @NotBlank String email,
                         @NotNull(message = "O perfil não pode ser NULL.") Perfil perfil
                        ){
}