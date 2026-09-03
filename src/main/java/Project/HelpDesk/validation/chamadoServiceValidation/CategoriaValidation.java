package Project.HelpDesk.validation.chamadoServiceValidation;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.entity.CategoriaEntity;
import Project.HelpDesk.enums.Categoria;
import Project.HelpDesk.handler.exception.BadRequestException;
import Project.HelpDesk.handler.exception.NotFoundException;
import Project.HelpDesk.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaValidation implements ValidacoesChamado<CategoriaEntity> {

    private final ICategoriaRepository categoriaRepository;

    public CategoriaEntity validar (ChamadoDto chamadoDto) {

        var cat = categoriaRepository.findById(chamadoDto.idCategoria())
                .orElseThrow(() -> new NotFoundException(String.format("Nenhuma categoria com o ID '%d' foi encontrada", chamadoDto.idCategoria())));

        var teste = cat.getChamados()
                .stream()
                .filter(x -> x.getTitulo().equalsIgnoreCase(chamadoDto.titulo()))
                .toList();

        if(!teste.isEmpty()) {
            throw new BadRequestException(String.format("Esse chamado ja possui a categoria '%s' cadastrada.", Categoria.pegarValor(cat.getNome())));
        }

        return cat;
    }
}
