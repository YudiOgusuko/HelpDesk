package Project.HelpDesk.repository;

import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.projection.ChamadoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

public interface IChamadoRepository extends JpaRepository<ChamadoEntity, Long> {

    @NativeQuery("""
    SELECT 
        ch.id AS chamadoId,
        ch.titulo AS chamadoTitulo,
        ch.descricao AS chamadoDescricao,
        ch.prioridade AS chamadoPrioridade,
        ch.status AS chamadoStatus,
        co.texto AS comentarioTexto,
        co.data_criacao AS comentarioData
                      
        FROM tb_chamados AS ch  
        JOIN tb_comentarios AS co 
            ON co.chamado_id = ch.id
                """)
    List<ChamadoProjection> projection();

    @NativeQuery(value = """
    SELECT 
        ch.id AS chamadoId,
        ch.titulo AS chamadoTitulo,
        ch.descricao AS chamadoDescricao,
        ch.prioridade AS chamadoPrioridade,
        ch.status AS chamadoStatus,
        co.texto AS comentarioTexto,
        co.data_criacao AS comentarioData
                      
        FROM tb_chamados AS ch  
        JOIN tb_comentarios AS co 
            ON co.chamado_id = ch.id
                """,
        countQuery = """
                SELECT(ch.id)
                FROM tb_chamados AS ch  
                JOIN tb_comentarios AS co 
                    ON co.chamado_id = ch.id
                """)
    Page<ChamadoProjection> pageable(Pageable pageable);
}
