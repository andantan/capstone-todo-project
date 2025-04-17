package org.zerock.todoserviceproject.domain.repository.extension;

import org.springframework.data.domain.Page;
import org.zerock.todoserviceproject.application.dto.todo.page.RequestPageDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;

public interface TodoRepositoryExtension {

    Page<ResponseQueryTodoDTO> requestFindAll(RequestPageDTO requestPageDTO);
}
