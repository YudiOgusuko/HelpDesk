package Project.HelpDesk.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({
        "chamadoId",
        "chamadoTitulo",
        "chamadoDescricao",
        "chamadoPrioridade",
        "chamadoStatus",
        "comentarioTexto",
        "comentarioData"
})
public interface ChamadoProjection {

    Long getChamadoId();
    String getChamadoTitulo();
    String getChamadoDescricao();
    String getChamadoPrioridade();
    String getChamadoStatus();
    String getComentarioTexto();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
    LocalDate getComentarioData();
}
