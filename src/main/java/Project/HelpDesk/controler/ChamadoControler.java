package Project.HelpDesk.controler;

import Project.HelpDesk.dto.ChamadoDto;
import Project.HelpDesk.entity.ChamadoEntity;
import Project.HelpDesk.projection.ChamadoProjection;
import Project.HelpDesk.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ChamadoEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChamadoEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/projection")
    @ResponseStatus(HttpStatus.OK)
    public List<ChamadoProjection> getProjection() {
        return service.getProjection();
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ChamadoProjection> getPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getPageable(page, size);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarChamado(@RequestBody ChamadoDto chamadoDto) {
        service.criarChamado(chamadoDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void alterarChamado(@PathVariable Long id,@Valid  @RequestBody ChamadoDto chamadoDto) {
        service.alterarChamado(id, chamadoDto);
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
