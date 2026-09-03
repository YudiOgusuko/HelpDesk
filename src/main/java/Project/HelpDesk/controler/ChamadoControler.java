package Project.HelpDesk.controler;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.projection.ChamadoProjection;
import Project.HelpDesk.service.ChamadoService;
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
@RequestMapping(value = "/v1/chamados")
@Validated
public class ChamadoControler {

    private final ChamadoService service;

    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/projection")
    public ResponseEntity<List<ChamadoProjection>> getProjection() {
        return ResponseEntity.ok(service.getProjection());
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    public ResponseEntity<Page<ChamadoProjection>> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(service.getPageable(page, size));
    }
    @PostMapping
    public ResponseEntity<ChamadoDto> criarChamado(@RequestBody ChamadoDto chamadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarChamado(chamadoDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> alterarChamado(@PathVariable Long id,@Valid  @RequestBody ChamadoDto chamadoDto) {
        return ResponseEntity.ok(service.alterarChamado(id, chamadoDto));
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
