package Project.HelpDesk.controler;

import Project.HelpDesk.dto.CategoriaDto;
import Project.HelpDesk.entity.CategoriaEntity;
import Project.HelpDesk.projection.CategoriaProjection;
import Project.HelpDesk.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/categorias")
@Validated
public class CategoriaControler {

    private final CategoriaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/projection")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaProjection> getProjection() {
        return service.getProjection();
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoriaProjection> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getPageable(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCategoria(@RequestBody CategoriaDto categoriaDto) {
        service.criarCategoria(categoriaDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDto categoriaDto) {
        service.alterarCategoria(id, categoriaDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }
}
