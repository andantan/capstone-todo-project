package org.zerock.todoserviceproject.domain.service.module.query;


import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;

public interface TodoQueryService {
    // +-+-+-+-+-+-+-+
    //      QUERY    |
    // +-+-+-+-+-+-+-+
    TodoDTO requestFindTodo(Long tno);

}
