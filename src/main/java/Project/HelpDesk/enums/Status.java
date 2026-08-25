package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {

    @JsonAlias({"ABERTO", "Aberto", "aberto"})
    ABERTO("Aberto"),

    @JsonAlias({"EM ANDAMENTO", "Em Andamento", "em andamento"})
    EM_ANDAMENTO("Em Andamento"),

    @JsonAlias({"RESOLVIDO", "Resolvido", "resolvido"})
    RESOLVIDO("Resolvido"),

    @JsonAlias({"FECHADO", "Fechado", "fechado"})
    FECHADO("Fechado");

    private String valor;

    public static String pegarValor(Status status) {
        for(Status sta : Status.values()) {
            if(status.equals(sta)) {
                return status.getValor();
            }
        }
        return null;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }
}