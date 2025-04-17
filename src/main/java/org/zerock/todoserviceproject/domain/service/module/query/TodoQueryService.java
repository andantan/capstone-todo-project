package org.zerock.todoserviceproject.domain.service.module.query;


import org.zerock.todoserviceproject.application.dto.todo.page.RequestFilterDTO;

import java.util.List;
import java.util.Map;

// +-+-+-+-+-+-+-+
//      QUERY    |
// +-+-+-+-+-+-+-+
public interface TodoQueryService {

    Map<String, String> requestQueryTodo(Long tno);

    List<Map<String, Object>> requestQueryTodoList(Boolean desc);
    List<Map<String, Object>> requestQueryTodoList(Boolean desc, Integer limit);

    List<Map<String, Object>> requestQueryPaginatedTodoList(Boolean desc, Integer page, Integer size);
    List<Map<String, Object>> requestQueryFilteredTodoList(Boolean desc, Integer page, Integer size, RequestFilterDTO requestFilterDTO);
}
