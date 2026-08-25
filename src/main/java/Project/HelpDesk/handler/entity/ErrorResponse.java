package Project.HelpDesk.handler.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    protected String message;
    protected Integer status;
}
