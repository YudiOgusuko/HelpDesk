package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@JsonPropertyOrder({
        "nome",
        "email",
        "perfil"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsuarioDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotNull(message = "O perfil não pode ser NULL.")
    private Perfil perfil;

}