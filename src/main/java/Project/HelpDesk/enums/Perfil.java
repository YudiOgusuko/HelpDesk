package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Perfil {

    CLIENTE("Cliente"),
    ATENDENTE("Atendente"),
    ADMIN("Admin");

    private String valor;

    @JsonCreator
    public static Perfil desserializar(String txt) {
        if(txt == null) {
            return null;
        }

        for(Perfil perfil: Perfil.values()) {
            if(perfil.name().equalsIgnoreCase(txt) || perfil.valor.equalsIgnoreCase(txt)) {
                return perfil;
            }
        }
        throw new IllegalStateException("Categoria Inválida: " + txt);
    }

    public static String pegarValor(Perfil perfil) {
        for(Perfil pef : Perfil.values()) {
            if(perfil.equals(pef)) {
                return pef.getValor();
            }
        }
        return null;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

}
