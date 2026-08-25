package Project.HelpDesk.service;

import Project.HelpDesk.dto.CategoriaDto;
import Project.HelpDesk.entity.CategoriaEntity;
import Project.HelpDesk.enums.Categoria;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.CategoriaProjection;
import Project.HelpDesk.repository.ICategoriaRepository;
import Project.HelpDesk.repository.IChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final IChamadoRepository chamadoRepository;

    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    public CategoriaEntity findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhuma categoria com o ID '%d' foi encontrada.", id)));
    }

    public List<CategoriaProjection> getProjection() {
        return categoriaRepository.projection();
    }

    public Page<CategoriaProjection> getPageable(Integer page, Integer size) {
        return categoriaRepository.pageable(PageRequest.of(page, size));
    }

    public void criarCategoria(CategoriaDto categoriaDto) {
         var categoria = categoriaRepository.findByNome(categoriaDto.getNome());

         if(categoria.isPresent()) {
           throw new NotFoundException(String.format("Categoria com o nome '%s' ja foi cadastrada.",
                     Categoria.pegarValor(categoriaDto.getNome())));
         }

         categoriaRepository.save(CategoriaEntity.builder()
                    .nome(categoriaDto.getNome())
                    .build());
    }

    public void alterarCategoria(Long id, CategoriaDto categoriaDto) {
        var categoria = findById(id);

        categoria.setNome(categoriaDto.getNome());
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public void deleteAll() {
        categoriaRepository.deleteAll();
    }

}