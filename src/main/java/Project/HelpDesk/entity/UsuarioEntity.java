package Project.HelpDesk.entity;

import Project.HelpDesk.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@JsonPropertyOrder({
        "id",
        "nome",
        "email",
        "perfil",
        "clientes",
        "atendentes",
        "comentarios"
})

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Perfil perfil;

    @OneToMany(mappedBy = "userCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ChamadoEntity> clientes;

    @OneToMany(mappedBy = "userAtendente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ChamadoEntity> atendentes;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<ComentarioEntity> comentarios;
}
