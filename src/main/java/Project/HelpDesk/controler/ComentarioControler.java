package Project.HelpDesk.controler;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.dto.ComentarioDto;
import Project.HelpDesk.dto.ComentarioUsuarioDto;
import Project.HelpDesk.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/comentarios")
@Validated
public class ComentarioControler {

    private final ComentarioService service;

    @GetMapping
    public ResponseEntity<List<ComentarioDto>> findAll() {
        service.findAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ComentarioDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/chamado")
    public ResponseEntity<ComentarioChamadoDto> criarComentarioChamado(@RequestBody ComentarioChamadoDto comentarioChamadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarComentarioChamado(comentarioChamadoDto));
    }

    @PostMapping(value = "/user")
    public ResponseEntity<ComentarioUsuarioDto> criarComentarioUser(@RequestBody ComentarioUsuarioDto comentarioUsuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarComentarioUser(comentarioUsuarioDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ComentarioDto> alterarComentario(@PathVariable Long id, @Valid @RequestBody ComentarioDto comentarioDto) {
        return ResponseEntity.ok(service.alterarComentario(id, comentarioDto));
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
