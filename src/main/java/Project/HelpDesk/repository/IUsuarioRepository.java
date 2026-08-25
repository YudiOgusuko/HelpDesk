package Project.HelpDesk.repository;

import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.projection.UsuarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("SELECT u FROM UsuarioEntity u JOIN FETCH u.clientes a WHERE a.titulo = :titulo")
    Optional<UsuarioEntity> findClientesFetch(String titulo);

    @Query("SELECT u FROM UsuarioEntity u JOIN FETCH u.atendentes a WHERE a.titulo = :titulo")
    Optional<UsuarioEntity> findAtendentesFetch(String titulo);

    Optional<UsuarioEntity> findByEmail(String email);

    @NativeQuery("""
        SELECT 
            u.id AS usuarioId,
            u.nome AS usuarioNome,
            u.email AS usuarioEmail,
            u.perfil AS usuarioPerfil,
            ch.titulo AS chamadaTitulo,
            ch.descricao AS chamadaDescricao,
            ch.prioridade AS chamadaPrioridade,
            ch.status AS chamadaStatus
                
        FROM tb_users AS u 
        JOIN tb_chamados AS ch
            ON ch.user_atendente_id = u.id 
            OR ch.user_cliente_id = u.id
                """)
    List<UsuarioProjection> projection();

    @NativeQuery(value = """
        SELECT 
            u.id AS usuarioId,
            u.nome AS usuarioNome,
            u.email AS usuarioEmail,
            u.perfil AS usuarioPerfil,
            ch.titulo AS chamadaTitulo,
            ch.descricao AS chamadaDescricao,
            ch.prioridade AS chamadaPrioridade,
            ch.status AS chamadaStatus
                
        FROM tb_users AS u 
        JOIN tb_chamados AS ch
            ON ch.user_atendente_id = u.id 
            OR ch.user_cliente_id = u.id
                """,

        countQuery = """
           SELECT (u.id)
           FROM tb_users AS u 
           JOIN tb_chamados AS ch
               ON ch.user_atendente_id = u.id 
               OR ch.user_cliente_id = u.id
                    """)
    Page<UsuarioProjection> paginacao(Pageable pageable);


}
