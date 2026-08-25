package Project.HelpDesk.service;

import Project.HelpDesk.dto.UsuarioDto;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.UsuarioProjection;
import Project.HelpDesk.repository.IChamadoRepository;
import Project.HelpDesk.repository.IComentarioRepository;
import Project.HelpDesk.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IChamadoRepository chamadoRepository;
    private final IComentarioRepository comentarioRepository;

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity findById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com id %d foi encontrado.", id)));
    }

    public List<UsuarioProjection> getProjection() {
        return usuarioRepository.projection();
    }

    public Page<UsuarioProjection> getPageable(Integer page, Integer size) {
        return usuarioRepository.paginacao(PageRequest.of(page, size));
    }

    @Transactional
    public void criarUser(UsuarioDto usuarioDto) {

         var userEmail = usuarioRepository.findByEmail(usuarioDto.getEmail());

         if(userEmail.isPresent()) {
             throw new BadRequestException(String.format("O e-mail '%s' já está sendo utilizado.", userEmail.get().getEmail()));
         }

        usuarioRepository.save(UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .perfil(usuarioDto.getPerfil())
                .build());
    }

    @Transactional
    public void alterarUser(Long id, UsuarioDto usuarioDto) {
        Optional<UsuarioEntity> userId = usuarioRepository.findById(id);

        if(userId.isEmpty()) {
            throw new BadRequestException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", id));
        }

        userId.get().setNome(usuarioDto.getNome());
        userId.get().setEmail(usuarioDto.getEmail());
        userId.get().setPerfil(usuarioDto.getPerfil());

        usuarioRepository.save(userId.get());
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
