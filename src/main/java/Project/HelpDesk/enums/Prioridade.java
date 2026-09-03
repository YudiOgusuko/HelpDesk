package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Prioridade {

    BAIXA("Baixa"),
    MEDIA("Media"),
    ALTA("Alta"),
    URGENTE("Urgente");

    private String valor;

    @JsonCreator
    public static Prioridade desserializar(String txt) {
        if(txt == null) {
            return null;
        }

        for(Prioridade prioridade: Prioridade.values()) {
            if(prioridade.name().equalsIgnoreCase(txt) || prioridade.valor.equalsIgnoreCase(txt)) {
                return prioridade;
            }
        }
        throw new IllegalStateException("Categoria Inválida: " + txt);
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }
}
