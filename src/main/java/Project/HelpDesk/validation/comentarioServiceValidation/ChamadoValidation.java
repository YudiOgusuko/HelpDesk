package Project.HelpDesk.validation.comentarioServiceValidation;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChamadoValidation implements ValidacoesComentarioChamado<ChamadoEntity, ComentarioChamadoDto> {

    private final IChamadoRepository chamadoRepository;

    @Override
    public ChamadoEntity validar(ComentarioChamadoDto comentarioChamadoDto) {

        if (comentarioChamadoDto.chamado() == null) {
            throw new BadRequestException("O ID do chamado é obrigatório.");
        }

        if (comentarioChamadoDto.user() == null) {
            throw new BadRequestException("O ID do usuário é obrigatório.");
        }

        var chamado = chamadoRepository.findById(comentarioChamadoDto.chamado())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", comentarioChamadoDto.chamado())));

        Long idCliente = chamado.getUserCliente() != null ? chamado.getUserCliente().getId() : null;
        Long idAtendente = chamado.getUserAtendente() != null ? chamado.getUserAtendente().getId() : null;

        if(!comentarioChamadoDto.user().equals(idAtendente) && !comentarioChamadoDto.user().equals(idCliente)) {
            throw new BadRequestException("O Id do usuário informado não possui o Id do chamado informado.");
        }

        var possuiChamado = chamado.getComentarios()
                .stream()
                .filter(x -> x.getTexto().equalsIgnoreCase(comentarioChamadoDto.texto()))
                .findFirst();

        if(possuiChamado.isPresent()) {
            throw new BadRequestException(String.format("O chamado com o Id '%d' já tem um comentário igual a esse.", possuiChamado.get().getChamado().getId()));
        }

        return chamado;

    }
}
