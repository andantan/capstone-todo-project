package org.zerock.todoserviceproject.domain.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

import org.zerock.todoserviceproject.domain.entity.base.BaseTImeEntity;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_todo_api")
public class TodoEntity extends BaseTImeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;


    @Column(
            name = "title",
            nullable = false,
            length = 100,
            columnDefinition = "VARCHAR(255) DEFAULT 'EMPTY'"
    )
    private String title;


    @Column(
            name = "due_date",
            nullable = false
    )
    private LocalDate dueDate;


    @Column(
            name = "writer",
            nullable = false,
            length = 50,
            columnDefinition = "VARCHAR(50) DEFAULT 'ANONYMOUS'"
    )
    private String writer;


    @Column(
            name = "complete",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT false"
    )
    private boolean complete;


    public void changeTitle(String title) { this.title = title; }
    public void changeDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void changeComplete(boolean complete) { this.complete = complete; }

}
