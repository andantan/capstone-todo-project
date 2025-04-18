package org.zerock.todoserviceproject.domain.repository.extension;

import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestQueryTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.util.List;

public interface TodoRepositoryExtension {

    List<TodoEntity> findListByDate(RequestQueryTodoDTO requestQueryTodoDTO);
}
