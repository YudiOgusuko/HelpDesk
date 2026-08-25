package Project.HelpDesk.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@JsonPropertyOrder({
        "texto",
        "chamado",
        "user"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ComentarioUsuarioDto {

    @NotBlank
    private String texto;

    @NotNull
    private Long user;
}
