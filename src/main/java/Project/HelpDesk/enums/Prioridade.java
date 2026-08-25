package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Prioridade {

    @JsonAlias({"BAIXA", "Baixa", "baixa"})
    BAIXA("Baixa"),

    @JsonAlias({"MEDIA", "Media", "media"})
    MEDIA("Media"),

    @JsonAlias({"ALTA", "Alta", "alta"})
    ALTA("Alta"),

    @JsonAlias({"URGENTE", "Urgente", "urgente"})
    URGENTE("Urgente");

    private String valor;

    public static String pegarValor(Prioridade prioridade) {
        for(Prioridade prio : Prioridade.values()) {
            if(prioridade.equals(prio)) {
                return prio.getValor();
            }
        }
        return null;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }
}
