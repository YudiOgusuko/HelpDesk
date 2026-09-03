package Project.HelpDesk.entity;

import Project.HelpDesk.enums.Prioridade;
import Project.HelpDesk.enums.Status;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@JsonPropertyOrder({
        "id",
        "titulo",
        "descricao",
        "prioridade",
        "status",
        "userCliente",
        "userAtendente",
        "categoria",
        "comentarios"
})

@Entity
@Table(name = "tb_chamados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class ChamadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String titulo;

    @NonNull
    @Column(nullable = false)
    private String descricao;

    @NonNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Prioridade prioridade;

    @NonNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userCliente_id")
    private UsuarioEntity userCliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userAtendente_id")
    private UsuarioEntity userAtendente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComentarioEntity> comentarios;
}
