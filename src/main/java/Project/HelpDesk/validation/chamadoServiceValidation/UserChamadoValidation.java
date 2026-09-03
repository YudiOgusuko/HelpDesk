package Project.HelpDesk.validation.chamadoServiceValidation;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.enums.Perfil;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserChamadoValidation implements ValidacoesChamado<UsuarioEntity> {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioEntity validar (ChamadoDto chamadoDto) {

        var user = usuarioRepository.findById(chamadoDto.idUsuario())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", chamadoDto.idUsuario())));

        Optional<UsuarioEntity> tituloDuplicado = user.getPerfil().equals(Perfil.CLIENTE)
                ? usuarioRepository.findClientesFetch(chamadoDto.titulo())
                : usuarioRepository.findAtendentesFetch(chamadoDto.titulo());

        if (tituloDuplicado.isPresent()) {
            throw new BadRequestException(
                    String.format("Título '%s' já foi cadastrado para o perfil com o ID '%d'.", chamadoDto.titulo(), user.getId()));
        }

        return user;

    }
}
