package org.zerock.todoserviceproject.domain.service.impl;

import org.zerock.todoserviceproject.application.todo.TodoDTO;

import java.util.Map;


public interface TodoService {
    // +-+-+-+-+-+-+-+
    //      QUERY    |
    // +-+-+-+-+-+-+-+
    TodoDTO requestFindTodo(Long tno);

}
