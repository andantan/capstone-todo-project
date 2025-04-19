package org.zerock.todoserviceproject.domain.service.module.query;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.map.ProjectionMapper;
import org.zerock.todoserviceproject.application.dto.todo.map.ResponseMapper;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestQueryTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoQueryServiceImpl implements TodoQueryService {

    private final TodoRepository todoRepository;
    private final ResponseMapper responseMapper;
    private final ProjectionMapper projectionMapper;


    @Override
    public Map<String, Object> requestQueryTodoList(RequestQueryTodoDTO requestQueryTodoDTO) {
        List<TodoDTO> todoDTOList = this.todoRepository.findListByDate(requestQueryTodoDTO).stream()
                .map(projectionMapper::mapToDTO)
                .toList();

        log.info(todoDTOList);

        List<ResponseQueryTodoDTO> responseBodyTodoDTOList = todoDTOList.stream()
                .map(responseMapper::mapToQueryResponseTodoDTO)
                .toList();

        List<Map<String, String>> responseMappedTodoDTOList = responseBodyTodoDTOList.stream()
                .map(responseMapper::getResponseMap)
                .toList();


        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("execution", "query");
        responseMap.put("list", responseMappedTodoDTOList);
        responseMap.put("status", "success");


        return responseMap;
    }
}
