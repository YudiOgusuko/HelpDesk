package Project.HelpDesk.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Perfil {

    @JsonAlias({"CLIENTE", "Cliente", "cliente"})
    CLIENTE("Cliente"),

    @JsonAlias({"ATENDENTE", "Atendente", "atendente"})
    ATENDENTE("Atendente"),

    @JsonAlias({"ADMIN", "Admin", "admin"})
    ADMIN("Admin");

    private String valor;

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
