package org.zerock.todoserviceproject.domain.service.module.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.map.RequestMapper;
import org.zerock.todoserviceproject.application.dto.todo.map.ResponseMapper;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestRegisterTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.Map;


@Service
@Log4j2
@RequiredArgsConstructor
public class TodoRegisterServiceImpl implements TodoRegisterService {

    private final TodoRepository todoRepository;
    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;
    private final ModelMapper modelMapper;


    @Override
    public Map<String, String> requestRegister(RequestRegisterTodoDTO requestRegisterTodoDTO) {
        TodoEntity resultEntity = this.todoRepository.save(
                this.modelMapper.map(
                        requestMapper.mapToTodoDTO(requestRegisterTodoDTO),
                        TodoEntity.class
                )
        );

        return responseMapper.getResponseMap(resultEntity);
    }
}
