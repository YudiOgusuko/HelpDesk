package Project.HelpDesk.controler;

import Project.HelpDesk.dto.UsuarioDto;
import Project.HelpDesk.projection.UsuarioProjection;
import Project.HelpDesk.service.UsuarioService;
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
@RequestMapping(value = "/v1/users")
@Validated
public class UsuarioControler {

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value = "/projection")
    public ResponseEntity<List<UsuarioProjection>> getProjection() {
        return ResponseEntity.ok(service.getProjection());
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    public ResponseEntity<Page<UsuarioProjection>> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(service.getPageable(page, size));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUser(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarUser(usuarioDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> alterarUser(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(service.alterarUser(id, usuarioDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
