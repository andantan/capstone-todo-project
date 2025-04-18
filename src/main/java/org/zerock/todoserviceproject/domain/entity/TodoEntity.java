package org.zerock.todoserviceproject.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import org.zerock.todoserviceproject.domain.entity.base.BaseTImeEntity;
import org.zerock.todoserviceproject.domain.entity.converter.LocalDateTimeConverter;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="todo_tbl")
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
            name = "date",
            nullable = false
    )
    private LocalDate date;


    @Column(
            name = "writer",
            nullable = false,
            length = 50,
            columnDefinition = "VARCHAR(64) DEFAULT 'ANONYMOUS'"
    )
    private String writer;


    @Column(
            name = "`from_time`",
            nullable = false,
            columnDefinition = "VARCHAR(16)"
    )
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime from;


    @Column(
            name = "to_time",
            nullable = false,
            columnDefinition = "VARCHAR(16)"
    )
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime to;


    @Column(
            name = "complete",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT false"
    )
    private boolean complete;


}
