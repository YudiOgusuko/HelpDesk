package Project.HelpDesk.validation.chamadoServiceValidation;

import Project.HelpDesk.dto.ChamadoDto;

public interface ValidacoesChamado<T> {

    T validar (ChamadoDto chamadoDto);
}
