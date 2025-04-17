package org.zerock.todoserviceproject.application.dto.todo.projection.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestModifyTodoDTO {
    @NotNull
    private Long tno;

    @Nullable
    private String title;

    @Nullable
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    boolean complete;
}
