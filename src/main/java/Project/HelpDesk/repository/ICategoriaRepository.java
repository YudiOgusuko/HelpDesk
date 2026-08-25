package Project.HelpDesk.repository;

import Project.HelpDesk.entity.CategoriaEntity;
import Project.HelpDesk.enums.Categoria;
import Project.HelpDesk.projection.CategoriaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNome(Categoria nome);

    @NativeQuery("""
        SELECT 
            ca.id AS categoriaId,
            ca.nome AS categoriaNome,
            ch.titulo AS chamadoTitulo,
            ch.descricao AS chamadoDescricao,
            ch.prioridade AS chamadoPrioridade,
            ch.status AS chamadoStatus
                
        FROM tb_categorias AS ca
        JOIN tb_chamados AS ch 
                ON ch.categoria_id = ca.id
        """)
    List<CategoriaProjection> projection();

    @NativeQuery(value = """
        SELECT 
            ca.id AS categoriaId,
            ca.nome AS categoriaNome,
            ch.titulo AS chamadoTitulo,
            ch.descricao AS chamadoDescricao,
            ch.prioridade AS chamadoPrioridade,
            ch.status AS chamadoStatus
                
        FROM tb_categorias AS ca
        JOIN tb_chamados AS ch 
                ON ch.categoria_id = ca.id
        """,
        countQuery = """
             SELECT(ca.id) 
             FROM tb_categorias AS ca
             JOIN tb_chamados AS ch 
                ON ch.categoria_id = ca.id
                """)
    Page<CategoriaProjection> pageable(Pageable pageable);
}
