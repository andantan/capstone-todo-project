package org.zerock.todoserviceproject.domain.service.impl;


import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;

public interface TodoService {
    // +-+-+-+-+-+-+-+
    //      QUERY    |
    // +-+-+-+-+-+-+-+
    TodoDTO requestFindTodo(Long tno);

}
