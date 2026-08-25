package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoriaDto {

    @NotNull
    private Categoria nome;

}
