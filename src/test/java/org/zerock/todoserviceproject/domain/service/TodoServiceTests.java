package org.zerock.todoserviceproject.domain.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.todoserviceproject.domain.service.impl.TodoService;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;


    @Test
    public void todoServiceTestQueryById() {
        log.info(
                "TodoServiceTests::todoServiceTestQueryById -> TodoDTO(tno=245) : {}",
                this.todoService.requestFindTodo(245L)
        );
        log.info(
                "TodoServiceTests::todoServiceTestQueryById -> TodoDTO(tno=425) : {}",
                this.todoService.requestFindTodo(425L)
        );
    }
}
