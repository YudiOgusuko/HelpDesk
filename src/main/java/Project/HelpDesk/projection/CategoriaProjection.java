package Project.HelpDesk.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "categoriaId",
        "categoriaNome",
        "chamadoTitulo",
        "chamadoDescricao",
        "chamadoPrioridade",
        "chamadoStatus"
})

public interface CategoriaProjection {

    Long getCategoriaId();
    String getCategoriaNome();
    String getChamadoTitulo();
    String getChamadoDescricao();
    String getChamadoPrioridade();
    String getChamadoStatus();
}
