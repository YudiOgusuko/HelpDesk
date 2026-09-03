package Project.HelpDesk.service;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.enums.Perfil;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.ChamadoProjection;
import Project.HelpDesk.repository.IChamadoRepository;
import Project.HelpDesk.validation.chamadoServiceValidation.CategoriaValidation;
import Project.HelpDesk.validation.chamadoServiceValidation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final IChamadoRepository chamadoRepository;
    private final UserValidation userValidation;
    private final CategoriaValidation categoriaValidation;
    private ChamadoEntity chamado;

    public List<ChamadoDto> findAll() {
        return chamadoRepository.findAll()
                .stream()
                .map(c -> new ChamadoDto(c.getId(), c.getTitulo(), c.getDescricao(), c.getPrioridade(), c.getStatus(), c.getCategoria().getId()))
                .toList();
    }

    public ChamadoDto findById(Long id) {
        var chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", id)));

        return ChamadoDto.builder()
                .idUsuario(chamado.getId()).titulo(chamado.getTitulo())
                .descricao(chamado.getDescricao()).prioridade(chamado.getPrioridade())
                .status(chamado.getStatus()).idCategoria(chamado.getCategoria().getId())
                .build();
    }

    public List<ChamadoProjection> getProjection() {
        return chamadoRepository.projection();
    }

    public Page<ChamadoProjection> getPageable(Integer page, Integer size) {
        return chamadoRepository.pageable(PageRequest.of(page, size));
    }

    @Transactional
    public ChamadoDto criarChamado(ChamadoDto chamadoDto) {

        var user = userValidation.validar(chamadoDto);
        var categoria = categoriaValidation.validar(chamadoDto);

        if (user.getPerfil().equals(Perfil.CLIENTE)) {
          chamado.criarCliente(chamadoDto, categoria, user);
        }
        else if (user.getPerfil().equals(Perfil.ATENDENTE)) {
            chamado.criarAtendente(chamadoDto, categoria, user);
        }
        else {
            throw new BadRequestException(String.format("O perfil '%s' não pode fazer um chamado.", Perfil.pegarValor(user.getPerfil())));
        }

        chamadoRepository.save(chamado);
        return chamadoDto;
    }

    @Transactional
    public ChamadoDto alterarChamado (Long id, ChamadoDto chamadoDto){

        chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhum chamado com o ID '%d' foi encontrado.", id)));

        chamado.atualizarChamado(chamadoDto);
        chamadoRepository.save(chamado);
        return chamadoDto;
    }

    @Transactional
    public void deleteById (Long id){
        chamadoRepository.deleteById(id);
    }


    public void deleteAll() {
        chamadoRepository.deleteAll();
    }

}
