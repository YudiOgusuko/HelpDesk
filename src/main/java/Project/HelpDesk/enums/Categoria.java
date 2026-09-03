package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Categoria {

    SOFTWARE("Software"),
    HARDWARE("Hardware"),
    REDE("Rede");

    private String valor;

    @JsonCreator
    public static Categoria desserializar(String txt) {
        if(txt == null) {
            return null;
        }

        for(Categoria categoria : Categoria.values()) {
            if(categoria.name().equalsIgnoreCase(txt) || categoria.valor.equalsIgnoreCase(txt)) {
                return categoria;
            }
        }
        throw new IllegalStateException("Categoria Inválida: " + txt);
    }

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