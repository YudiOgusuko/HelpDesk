package Project.HelpDesk.service;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.dto.ComentarioUsuarioDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.entity.ComentarioEntity;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.enums.Perfil;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.IChamadoRepository;
import Project.HelpDesk.repository.IComentarioRepository;
import Project.HelpDesk.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final IComentarioRepository comentarioRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IChamadoRepository chamadoRepository;


    public List<ComentarioEntity> findAll() {
        return comentarioRepository.findAll();
    }

    public ComentarioEntity findById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Comentário com o Id '%d' não foi encontrado.", id)));
    }

    @Transactional
    public void criarComentarioChamado(ComentarioChamadoDto comentarioChamadoDto) {

        var users = usuarioRepository.findById(comentarioChamadoDto.getUser())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", comentarioChamadoDto.getUser())));

        var chamado = chamadoRepository.findById(comentarioChamadoDto.getChamado())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", comentarioChamadoDto.getChamado())));

        if(users.getPerfil().equals(Perfil.ADMIN)) {
            throw new BadRequestException(String.format("O perfil '%s' não pode realizar um comentário", Perfil.pegarValor(users.getPerfil())));
        }

        Optional<ComentarioEntity> comentarioIgual = users.getComentarios()
                                                    .stream()
                                                    .filter(x -> x.getTexto().equalsIgnoreCase(comentarioChamadoDto.getTexto()))
                                                    .findFirst();
        if(comentarioIgual.isPresent()) {
            throw new BadRequestException(String.format("O usuário com o Id '%d' já tem um comentário igual a esse.", comentarioChamadoDto.getUser()));
        }

        Optional<ComentarioEntity> possuiChamado = chamado.getComentarios()
                                .stream()
                                .filter(x -> x.getTexto().equalsIgnoreCase(comentarioChamadoDto.getTexto()))
                                .findFirst();

        if(possuiChamado.isPresent()) {
            throw new BadRequestException(String.format("O chamado com o Id '%d' já tem um comentário igual a esse.", comentarioChamadoDto.getChamado()));
        }

        Optional<Long> acharIdCliente = Optional.ofNullable(chamado)
                .map(ChamadoEntity::getUserCliente)
                .map(UsuarioEntity::getId);

        Optional<Long> acharIdAtendente = Optional.ofNullable(chamado)
                .map(ChamadoEntity::getUserAtendente)
                .map(UsuarioEntity::getId);

        Long idAtendente = acharIdAtendente.orElse(0L);
        Long idCliente = acharIdCliente.orElse(0L);


        if(!comentarioChamadoDto.getUser().equals(idAtendente) && !comentarioChamadoDto.getUser().equals(idCliente)) {
            throw new BadRequestException("O Id do usuário informado não possui o Id do chamado informado.");
        }

        comentarioRepository.save(ComentarioEntity
                .builder()
                .texto(comentarioChamadoDto.getTexto())
                .chamado(chamado)
                .build());
    }

    @Transactional
    public void criarComentarioUser(ComentarioUsuarioDto comentarioUsuarioDto) {

        var user = usuarioRepository.findById(comentarioUsuarioDto.getUser())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", comentarioUsuarioDto.getUser())));


        if(user.getPerfil().equals(Perfil.ADMIN)) {
            throw new BadRequestException(String.format("O perfil '%s' não pode realizar um comentário", Perfil.pegarValor(user.getPerfil())));
        }

        Optional<ComentarioEntity> comentarioIgual = user.getComentarios()
                .stream()
                .filter(x -> x.getTexto().equalsIgnoreCase(comentarioUsuarioDto.getTexto()))
                .findFirst();

        if(comentarioIgual.isPresent()) {
            throw new BadRequestException(String.format("O usuário com o Id '%d' já tem um comentário igual a esse.", comentarioUsuarioDto.getUser()));
        }

       comentarioRepository.save(ComentarioEntity.builder()
                .texto(comentarioUsuarioDto.getTexto())
                .user(user)
                .build());
    }
    @Transactional
    public void alterarComentario(Long id, ComentarioChamadoDto comentarioChamadoDto) {
        var comentario = findById(id);

        comentario.setTexto(comentarioChamadoDto.getTexto());
        comentarioRepository.save(ComentarioEntity
                .builder()
                .texto(comentarioChamadoDto.getTexto())
                .build());
    }

    public void deleteAll() {
        comentarioRepository.deleteAll();
    }

    @Transactional
    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }
}
