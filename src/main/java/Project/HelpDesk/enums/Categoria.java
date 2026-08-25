package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Categoria {

    @JsonAlias({"SOFTWARE", "Software", "software"})
    SOFTWARE("Software"),

    @JsonAlias({"HARDWARE", "Hardware", "hardware"})
    HARDWARE("Hardware"),

    @JsonAlias({"REDE", "Rede", "rede"})
    REDE("Rede");

    private String valor;

    public static String pegarValor(Categoria categoria) {
        for(Categoria cat : Categoria.values()) {
            if(categoria.equals(cat)) {
                return cat.getValor();
            }
        }
        return null;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }
}