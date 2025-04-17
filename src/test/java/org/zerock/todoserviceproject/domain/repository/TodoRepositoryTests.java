package org.zerock.todoserviceproject.domain.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;


    @Test
    public void TodoRepositoryTestInsert() {
        IntStream.rangeClosed(1, 256).forEach(i -> {

            TodoEntity todo = TodoEntity.builder()
                    .title(MessageFormat.format("todo dummy - {0}", i))
                    .dueDate(LocalDate.of(2025, (i % 8) + 4, (i % 30) + 1 ))
                    .writer(MessageFormat.format("writer dummy - {0}", i))
                    .build();

            todoRepository.save(todo);
        });
    }
}
