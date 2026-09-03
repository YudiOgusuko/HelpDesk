package Project.HelpDesk.entity;

import Project.HelpDesk.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@JsonPropertyOrder({
        "id",
        "nome",
        "chamados"
})

@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private Categoria nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<ChamadoEntity> chamados;
}
