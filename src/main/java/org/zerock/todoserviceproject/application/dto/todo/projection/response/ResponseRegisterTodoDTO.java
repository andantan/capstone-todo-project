package org.zerock.todoserviceproject.application.dto.todo.projection.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegisterTodoDTO {
    private Long tno;

    private String title;
    private String writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean complete;
}
