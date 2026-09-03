package Project.HelpDesk.service;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.dto.ComentarioDto;
import Project.HelpDesk.dto.ComentarioUsuarioDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.entity.ComentarioEntity;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IComentarioRepository;
import Project.HelpDesk.validation.comentarioServiceValidation.ChamadoValidation;
import Project.HelpDesk.validation.comentarioServiceValidation.UserComentarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final IComentarioRepository comentarioRepository;
    private final ChamadoValidation chamadoValidation;
    private final UserComentarioValidation userComentarioValidation;

    public List<ComentarioDto> findAll() {
        return comentarioRepository.findAll()
                .stream()
                .map(c -> new ComentarioDto(c.getTexto(), c.getDataCriacao()))
                .toList();
    }

    public ComentarioDto findById(Long id) {
        ComentarioEntity comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Comentário com o Id '%d' não foi encontrado.", id)));

        return ComentarioDto.builder()
                .texto(comentario.getTexto()).dataCriacao(comentario.getDataCriacao())
                .build();
    }

    @Transactional
    public ComentarioChamadoDto criarComentarioChamado(ComentarioChamadoDto comentarioChamadoDto) {

        userComentarioValidation.validar(comentarioChamadoDto);
        ChamadoEntity chamado = chamadoValidation.validar(comentarioChamadoDto);

        comentarioRepository.save(ComentarioEntity
                .builder()
                .texto(comentarioChamadoDto.texto())
                .chamado(chamado)
                .build());

        return comentarioChamadoDto;
    }

    @Transactional
    public ComentarioUsuarioDto criarComentarioUser(ComentarioUsuarioDto comentarioUsuarioDto) {

       var user = userComentarioValidation.validar(comentarioUsuarioDto);

       comentarioRepository.save(ComentarioEntity.builder()
                .texto(comentarioUsuarioDto.texto())
                .user(user)
                .build());

        return comentarioUsuarioDto;
    }

    @Transactional
    public ComentarioDto alterarComentario(Long id, ComentarioDto comentarioDto) {

        ComentarioEntity comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Comentário com o Id '%d' não foi encontrado.", id)));

        comentario.setTexto(comentarioDto.texto());
        comentarioRepository.save(comentario);
        return comentarioDto;
    }

    @Transactional
    public void deleteAll() {
        comentarioRepository.deleteAll();
    }

    @Transactional
    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }
}
