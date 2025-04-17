package org.zerock.todoserviceproject.domain.service.module.modify;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.map.RequestMapper;
import org.zerock.todoserviceproject.application.dto.todo.map.ResponseMapper;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestModifyTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseModifyTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoModifyServiceImpl implements TodoModifyService {

    private final TodoRepository todoRepository;
    private final ResponseMapper responseMapper;
    private final ModelMapper modelMapper;

    @Override
    public Map<String, String> requestUpdateTodo(RequestModifyTodoDTO requestModifyTodoDTO) {
        TodoDTO targetTodoDTO = this.todoRepository.findById(requestModifyTodoDTO.getTno())   // Query
                .map(todo -> modelMapper.map(todo, TodoDTO.class))  // If exist then mapping
                .orElseThrow(() -> new NoSuchElementException("Todo tuple not found: " + requestModifyTodoDTO.getTno())); // else throw exception

        if (requestModifyTodoDTO.getTitle() != null) {
            targetTodoDTO.changeTitle(requestModifyTodoDTO.getTitle());
        }

        if (requestModifyTodoDTO.getDueDate() != null) {
            targetTodoDTO.changeDueDate(requestModifyTodoDTO.getDueDate());
        }

        if (requestModifyTodoDTO.isComplete()) {
            targetTodoDTO.changeComplete(true);
        }

        TodoEntity resultEntity = this.todoRepository.save(
                this.modelMapper.map(targetTodoDTO, TodoEntity.class)
        );

        ResponseModifyTodoDTO responseModifyTodoDTO = this.responseMapper.mapToModifyResponseTodoDTO(
                this.modelMapper.map(resultEntity, TodoDTO.class)
        );

        return this.responseMapper.getResponseMap(responseModifyTodoDTO);
    }
}
