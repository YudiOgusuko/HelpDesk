package Project.HelpDesk.entity;

import Project.HelpDesk.enums.Prioridade;
import Project.HelpDesk.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userCliente_id")
    @JsonBackReference
    private UsuarioEntity userCliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAtendente_id")
    @JsonBackReference
    private UsuarioEntity userAtendente;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ComentarioEntity> comentarios;
}
