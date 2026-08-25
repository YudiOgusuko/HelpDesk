package Project.HelpDesk.repository;

import Project.HelpDesk.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

}
