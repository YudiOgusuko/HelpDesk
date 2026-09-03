package Project.HelpDesk.validation.comentarioServiceValidation;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChamadoValidation implements ValidacoesComentarioChamado<ChamadoEntity, ComentarioChamadoDto> {

    private final IChamadoRepository chamadoRepository;

    @Override
    public ChamadoEntity validar(ComentarioChamadoDto comentarioChamadoDto) {

        ChamadoEntity chamado = chamadoRepository.findById(comentarioChamadoDto.chamado())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", comentarioChamadoDto.chamado())));

        Optional<Long> acharIdCliente = Optional.ofNullable(chamado)
                .map(ChamadoEntity::getUserCliente)
                .map(UsuarioEntity::getId);

        Optional<Long> acharIdAtendente = Optional.ofNullable(chamado)
                .map(ChamadoEntity::getUserAtendente)
                .map(UsuarioEntity::getId);

        Long idAtendente = acharIdAtendente.orElse(0L);
        Long idCliente = acharIdCliente.orElse(0L);

        if(!comentarioChamadoDto.user().equals(idAtendente) && !comentarioChamadoDto.user().equals(idCliente)) {
            throw new BadRequestException("O Id do usuário informado não possui o Id do chamado informado.");
        }

        return chamado;

    }

}
