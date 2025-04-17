package org.zerock.todoserviceproject.domain.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;
import org.zerock.todoserviceproject.domain.service.impl.TodoService;

import java.util.NoSuchElementException;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;


    @Override
    public TodoDTO requestFindTodo(Long tno) {
        TodoDTO resultTodoDTO = this.todoRepository.findById(tno)   // Query
                .map(todo -> modelMapper.map(todo, TodoDTO.class))  // If exist then mapping
                .orElseThrow(() -> new NoSuchElementException("Todo tuple not found: " + tno)); // else throw exception

        return resultTodoDTO;
    }
}
