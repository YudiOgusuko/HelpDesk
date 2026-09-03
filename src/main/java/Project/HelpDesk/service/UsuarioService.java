package Project.HelpDesk.service;

import Project.HelpDesk.dto.UsuarioDto;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.UsuarioProjection;
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

    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioDto(u.getNome(), u.getEmail(), u.getPerfil()))
                .toList();
    }

    public UsuarioDto findById(Long id){
        var user = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com id %d foi encontrado.", id)));

        return UsuarioDto.builder()
                .nome(user.getNome()).email(user.getEmail()).perfil(user.getPerfil())
                .build();
    }

    public List<UsuarioProjection> getProjection() {
        return usuarioRepository.projection();
    }

    public Page<UsuarioProjection> getPageable(Integer page, Integer size) {
        return usuarioRepository.paginacao(PageRequest.of(page, size));
    }

    @Transactional
    public UsuarioDto criarUser(UsuarioDto usuarioDto) {

         var userEmail = usuarioRepository.findByEmail(usuarioDto.email());

         if(userEmail.isPresent()) {
             throw new BadRequestException(String.format("O e-mail '%s' já está sendo utilizado.", userEmail.get().getEmail()));
         }

        usuarioRepository.save(UsuarioEntity.builder()
                .nome(usuarioDto.nome()).email(usuarioDto.email()).perfil(usuarioDto.perfil())
                .build());

        return usuarioDto;
    }

    @Transactional
    public UsuarioDto alterarUser(Long id, UsuarioDto usuarioDto) {
        var user = usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", id)));

        user.setNome(usuarioDto.nome());
        user.setEmail(usuarioDto.email());
        user.setPerfil(usuarioDto.perfil());

        usuarioRepository.save(user);
        return usuarioDto;
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
