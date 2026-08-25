package Project.HelpDesk.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "usuarioId",
        "usuarioNome",
        "usuarioEmail",
        "usuarioPerfil",
        "chamadaTitulo",
        "chamadaDescricao",
        "chamadaPrioridade",
        "chamadaStatus"
})
public interface UsuarioProjection {

    Long getUsuarioId();
    String getUsuarioNome();
    String getUsuarioEmail();
    String getUsuarioPerfil();
    String getChamadaTitulo();
    String getChamadaDescricao();
    String getChamadaPrioridade();
    String getChamadaStatus();

}
