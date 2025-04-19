package org.zerock.todoserviceproject.application.dto.todo.projection.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class RequestQueryTodoDTO {
    private String writer;
    private LocalDate date;
}
