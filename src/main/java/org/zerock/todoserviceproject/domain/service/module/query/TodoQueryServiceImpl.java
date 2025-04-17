package org.zerock.todoserviceproject.domain.service.module.query;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.map.ResponseMapper;
import org.zerock.todoserviceproject.application.dto.todo.page.RequestPageDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoQueryServiceImpl implements TodoQueryService {

    private final TodoRepository todoRepository;
    private final ResponseMapper responseMapper;
    private final ModelMapper modelMapper;


    @Override
    public Map<String, String> requestQueryTodo(Long tno) {
        TodoDTO resultTodoDTO = this.todoRepository.findById(tno)   // Query
                .map(todo -> modelMapper.map(todo, TodoDTO.class))  // If exist then mapping
                .orElseThrow(() -> new NoSuchElementException("Todo tuple not found: " + tno)); // else throw exception

        ResponseQueryTodoDTO responseQueryTodoDTO = this.responseMapper.mapToQueryResponseTodoDTO(resultTodoDTO);

        return responseMapper.getResponseMap(responseQueryTodoDTO, "query", "success");
    }


    @Override
    public List<Map<String, Object>> requestQueryTodoList(Boolean desc) {
        Sort orderBy = desc
                ? Sort.by(Sort.Direction.DESC, "tno")
                : Sort.by(Sort.Direction.ASC, "tno");

        List<TodoDTO> todoDTOList = this.todoRepository.findAll(orderBy).stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .toList();

        List<ResponseQueryTodoDTO> responseBodyTodoDTOList = todoDTOList.stream()
                .map(responseMapper::mapToQueryResponseTodoDTO)
                .toList();

        return responseMapper.getResponseMap(responseBodyTodoDTOList);
    }

    @Override
    public List<Map<String, Object>> requestQueryTodoList(Boolean desc, Integer limit) {
        RequestPageDTO requestPageDTO = RequestPageDTO.builder().size(limit).desc(desc).build();

        List<TodoDTO> todoDTOList = this.todoRepository.findAll(requestPageDTO.getPageable("tno"))
                .stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .toList();

        List<ResponseQueryTodoDTO> responseBodyTodoDTOList = todoDTOList.stream()
                .map(responseMapper::mapToQueryResponseTodoDTO)
                .toList();

        return responseMapper.getResponseMap(responseBodyTodoDTOList);
    }
}
