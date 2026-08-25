package Project.HelpDesk.service;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.enums.Categoria;
import Project.HelpDesk.enums.Perfil;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.ChamadoProjection;
import Project.HelpDesk.repository.ICategoriaRepository;
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
public class ChamadoService {

    private final IChamadoRepository chamadoRepository;
    private final ICategoriaRepository categoriaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IComentarioRepository comentarioRepository;


    public List<ChamadoEntity> findAll() {
        return chamadoRepository.findAll();
    }

    public ChamadoEntity findById(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", id)));
    }

    public List<ChamadoProjection> getProjection() {
        return chamadoRepository.projection();
    }

    public Page<ChamadoProjection> getPageable(Integer page, Integer size) {
        return chamadoRepository.pageable(PageRequest.of(page, size));
    }

    @Transactional
    public void criarChamado(ChamadoDto chamadoDto) {

        Optional<UsuarioEntity> tituloDuplicado;
        ChamadoEntity chamado = new ChamadoEntity();

        var user = usuarioRepository.findById(chamadoDto.getIdUsuario())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum usuário com o ID '%d' foi encontrado.", chamadoDto.getIdUsuario())));

        var categoria = categoriaRepository.findById(chamadoDto.getIdCategoria())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhuma categoria com o ID '%d' foi encontrada", chamadoDto.getIdCategoria())));

        var teste = categoria.getChamados()
                .stream()
                .filter(x -> x.getTitulo().equalsIgnoreCase(chamadoDto.getTitulo()))
                .toList();

        if(!teste.isEmpty()) {
            throw new BadRequestException(String.format("Esse chamado ja possui a categoria '%s' cadastrada.", Categoria.pegarValor(categoria.getNome())));
        }

        if (user.getPerfil().equals(Perfil.CLIENTE)) {
            tituloDuplicado = usuarioRepository.findClientesFetch(chamadoDto.getTitulo());

            if(tituloDuplicado.isPresent()) {
                throw new BadRequestException(String.format("Título '%s' ja foi cadastrado para o perfil '%s'", chamadoDto.getTitulo(), Perfil.pegarValor(tituloDuplicado.get().getPerfil())));
            }

            chamado.setTitulo(chamadoDto.getTitulo());
            chamado.setDescricao(chamadoDto.getDescricao());
            chamado.setPrioridade(chamadoDto.getPrioridade());
            chamado.setStatus(chamadoDto.getStatus());
            chamado.setCategoria(categoria);
            chamado.setUserCliente(user);
        }
        else if (user.getPerfil().equals(Perfil.ATENDENTE)) {
            tituloDuplicado = usuarioRepository.findAtendentesFetch((chamadoDto.getTitulo()));

            if(tituloDuplicado.isPresent()) {
                throw new BadRequestException(String.format("Título '%s' ja foi cadastrado para o perfil '%s'", chamadoDto.getTitulo(), Perfil.pegarValor(tituloDuplicado.get().getPerfil())));
            }

            chamado.setTitulo(chamadoDto.getTitulo());
            chamado.setDescricao(chamadoDto.getDescricao());
            chamado.setPrioridade(chamadoDto.getPrioridade());
            chamado.setStatus(chamadoDto.getStatus());
            chamado.setCategoria(categoria);
            chamado.setUserAtendente(user);
        }
        else {
            throw new BadRequestException(String.format("O perfil '%s' não pode fazer um chamado.", Perfil.pegarValor(user.getPerfil())));
        }

        chamadoRepository.save(chamado);
    }

    @Transactional
    public void alterarChamado (Long id, ChamadoDto chamadoDto){

        var chamado = findById(id);

        chamado.setTitulo(chamadoDto.getTitulo());
        chamado.setDescricao(chamadoDto.getDescricao());
        chamado.setPrioridade(chamadoDto.getPrioridade());
        chamado.setStatus(chamadoDto.getStatus());

        chamadoRepository.save(chamado);
    }

    @Transactional
    public void deleteById (Long id){
        chamadoRepository.deleteById(id);
    }


    public void deleteAll() {
        chamadoRepository.deleteAll();
    }

}
