package Project.HelpDesk.validation.comentarioServiceValidation;

import Project.HelpDesk.dto.ComentarioBaseDto;

public interface ValidacoesComentarioChamado<T, D extends ComentarioBaseDto> {

    T validar(D comentarioDto);
}
