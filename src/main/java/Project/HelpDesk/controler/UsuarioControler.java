package Project.HelpDesk.controler;

import Project.HelpDesk.dto.UsuarioDto;
import Project.HelpDesk.entity.UsuarioEntity;
import Project.HelpDesk.projection.UsuarioProjection;
import Project.HelpDesk.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UsuarioEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/projection")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioProjection> getProjection() {
        return service.getProjection();
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioProjection> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getPageable(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUser(@RequestBody UsuarioDto usuarioDto) {
        service.criarUser(usuarioDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarUser(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        service.alterarUser(id, usuarioDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
