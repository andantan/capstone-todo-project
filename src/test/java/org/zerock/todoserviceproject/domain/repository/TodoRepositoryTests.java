package org.zerock.todoserviceproject.domain.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void TodoRepositoryTestInsert() {
        LocalDateTime from = convertToEntityAttribute("2025-04-18 18:00");
        LocalDateTime to = convertToEntityAttribute("2025-04-19 11:00");
        
        IntStream.rangeClosed(1, 180).forEach(i -> {

            TodoEntity todo = TodoEntity.builder()
                    .writer(MessageFormat.format("writer dummy - {0}", (i % 30) + 1 ))
                    .title(MessageFormat.format("todo dummy - {0}", (i % 30) + 1 ))
                    .date(LocalDate.of(2025, (i % 8) + 4, (i % 30) + 1 ))
                    .from(from)
                    .to(to)
                    .build();

            todoRepository.save(todo);
        });

    }

    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return (attribute != null) ? attribute.format(FORMATTER) : null;
    }

    public LocalDateTime convertToEntityAttribute(String dbData) {
        return (dbData != null) ? LocalDateTime.parse(dbData, FORMATTER) : null;
    }
}
