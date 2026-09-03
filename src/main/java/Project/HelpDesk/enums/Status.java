package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {

    ABERTO("Aberto"),
    EM_ANDAMENTO("Em Andamento"),
    RESOLVIDO("Resolvido"),
    FECHADO("Fechado");

    private String valor;

    @JsonCreator
    public static Status desserializar(String txt) {
        if(txt == null) {
            return null;
        }

        for(Status status: Status.values()) {
            if(status.name().equalsIgnoreCase(txt) || status.valor.equalsIgnoreCase(txt)) {
                return status;
            }
        }
        throw new IllegalStateException("Categoria Inválida: " + txt);
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }
}