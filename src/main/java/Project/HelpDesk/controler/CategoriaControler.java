package Project.HelpDesk.controler;

import Project.HelpDesk.dto.CategoriaDto;
import Project.HelpDesk.projection.CategoriaProjection;
import Project.HelpDesk.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoriaDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/projection")
    public ResponseEntity<List<CategoriaProjection>> getProjection() {
        return ResponseEntity.ok(service.getProjection());
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    public ResponseEntity<Page<CategoriaProjection>> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(service.getPageable(page, size));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> criarCategoria(@RequestBody CategoriaDto categoriaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCategoria(categoriaDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> alterarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDto categoriaDto) {
        return ResponseEntity.ok(service.alterarCategoria(id, categoriaDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
