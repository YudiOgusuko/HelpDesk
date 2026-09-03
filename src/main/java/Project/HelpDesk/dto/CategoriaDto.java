package Project.HelpDesk.dto;

import Project.HelpDesk.enums.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoriaDto(@NotNull Categoria nome) {

}
