package org.zerock.todoserviceproject.domain.service.module.query;


import java.util.List;
import java.util.Map;

// +-+-+-+-+-+-+-+
//      QUERY    |
// +-+-+-+-+-+-+-+
public interface TodoQueryService {

    Map<String, String> requestQueryTodo(Long tno);

    List<Map<String, Object>> requestQueryTodoList(Boolean desc);
    List<Map<String, Object>> requestQueryTodoList(Boolean desc, Integer limit);
}
