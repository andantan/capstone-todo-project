package org.zerock.todoserviceproject.application.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDTO {
    @ToString.Exclude private Long tno;

    private String title;
    private String writer;

    private LocalDate dueDate;

    private boolean complete;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ToString.Exclude private LocalDateTime regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ToString.Exclude private LocalDateTime modDate;


    public void changeTitle(String title) { this.title = title; }
    public void changeDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void changeComplete(boolean complete) { this.complete = complete; }
}
