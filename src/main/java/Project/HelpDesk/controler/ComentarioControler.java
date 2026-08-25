package Project.HelpDesk.controler;

import Project.HelpDesk.dto.ComentarioChamadoDto;
import Project.HelpDesk.dto.ComentarioUsuarioDto;
import Project.HelpDesk.entity.ComentarioEntity;
import Project.HelpDesk.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ComentarioEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComentarioEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/chamado")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarComentarioChamado(@RequestBody ComentarioChamadoDto comentarioChamadoDto) {
        service.criarComentarioChamado(comentarioChamadoDto);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarComentarioUser(@RequestBody ComentarioUsuarioDto comentarioUsuarioDto) {
        service.criarComentarioUser(comentarioUsuarioDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarComentario(@PathVariable Long id, @Valid @RequestBody ComentarioChamadoDto comentarioChamadoDto) {
        service.alterarComentario(id, comentarioChamadoDto);
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
