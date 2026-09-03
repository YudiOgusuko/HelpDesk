package Project.HelpDesk.service;

import Project.HelpDesk.dto.CategoriaDto;
import Project.HelpDesk.entity.CategoriaEntity;
import Project.HelpDesk.enums.Categoria;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.projection.CategoriaProjection;
import Project.HelpDesk.repository.ICategoriaRepository;
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

    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream().map(c -> new CategoriaDto(c.getNome())).toList();
    }

    public CategoriaDto findById(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhuma categoria com o ID '%d' foi encontrada.", id)));

        return CategoriaDto.builder()
                .nome(categoria.getNome())
                .build();
    }

    public List<CategoriaProjection> getProjection() {
        return categoriaRepository.projection();
    }

    public Page<CategoriaProjection> getPageable(Integer page, Integer size) {
        return categoriaRepository.pageable(PageRequest.of(page, size));
    }

    @Transactional
    public CategoriaDto criarCategoria(CategoriaDto categoriaDto) {
         var categoria = categoriaRepository.findByNome(categoriaDto.nome());

         if(categoria.isPresent()) {
           throw new NotFoundException(String.format("Categoria com o nome '%s' ja foi cadastrada.",
                     Categoria.pegarValor(categoriaDto.nome())));
         }

         categoriaRepository.save(CategoriaEntity.builder()
                    .nome(categoriaDto.nome())
                    .build());

         return categoriaDto;
    }

    @Transactional
    public CategoriaDto alterarCategoria(Long id, CategoriaDto categoriaDto) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Nenhuma categoria com o ID '%d' foi encontrada.", id)));

        categoria.setNome(categoriaDto.nome());
        categoriaRepository.save(categoria);

        return categoriaDto;
    }

    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        categoriaRepository.deleteAll();
    }

}