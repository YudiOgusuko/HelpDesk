package Project.HelpDesk.validation.comentarioServiceValidation;

import Project.HelpDesk.dto.ComentarioBaseDto;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.enums.Perfil;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserComentarioValidation implements ValidacoesComentarioChamado<UsuarioEntity, ComentarioBaseDto> {

    private final IUsuarioRepository usuarioRepository;
    private final ChamadoValidation chamadoValidation;

    @Override
    public UsuarioEntity validar(ComentarioBaseDto comentarioDto) {

        var user = usuarioRepository.findById(comentarioDto.user())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", comentarioDto.user())));

        if(user.getPerfil().equals(Perfil.ADMIN)) {
            throw new BadRequestException(String.format("O perfil '%s' não pode realizar um comentário", Perfil.pegarValor(user.getPerfil())));
        }

       var comentarioIgual = user.getComentarios()
                .stream()
                .filter(x -> x.getTexto().equalsIgnoreCase(comentarioDto.texto()))
                .findFirst();
        if(comentarioIgual.isPresent()) {
            throw new BadRequestException(String.format("O usuário com o Id '%d' já tem um comentário igual a esse.", comentarioDto.user()));
        }

        return user;
    }
}
