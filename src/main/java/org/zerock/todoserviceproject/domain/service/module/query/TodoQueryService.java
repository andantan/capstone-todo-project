package org.zerock.todoserviceproject.domain.service.module.query;


import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;

import java.util.Map;

// +-+-+-+-+-+-+-+
//      QUERY    |
// +-+-+-+-+-+-+-+
public interface TodoQueryService {

    Map<String, String> requestFindTodo(Long tno);

}
