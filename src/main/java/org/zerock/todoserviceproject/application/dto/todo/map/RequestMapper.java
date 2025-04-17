package org.zerock.todoserviceproject.application.dto.todo.map;


import org.springframework.stereotype.Component;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestRegisterTodoDTO;

@Component
public class RequestMapper {

    public TodoDTO mapToTodoDTO(RequestRegisterTodoDTO requestRegisterTodoDTO) {
        return TodoDTO.builder()
                .title(requestRegisterTodoDTO.getTitle())
                .writer(requestRegisterTodoDTO.getWriter())
                .dueDate(requestRegisterTodoDTO.getDueDate())
                .build();
    }

}
