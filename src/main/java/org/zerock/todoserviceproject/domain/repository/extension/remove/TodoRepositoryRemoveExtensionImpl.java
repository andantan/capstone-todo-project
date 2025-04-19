package org.zerock.todoserviceproject.domain.repository.extension.remove;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestRemoveTodoDTO;
import org.zerock.todoserviceproject.domain.entity.QTodoEntity;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;


public class TodoRepositoryRemoveExtensionImpl extends QuerydslRepositorySupport
        implements TodoRepositoryRemoveExtension {

    public TodoRepositoryRemoveExtensionImpl() {
        super(TodoEntity.class);
    }

    @Override
    public TodoEntity RetriveTupleAndRemove(RequestRemoveTodoDTO requestRemoveTodoDTO) {
        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        JPQLQuery<TodoEntity> query = from(todoEntity);

        query.where(todoEntity.tno.eq(requestRemoveTodoDTO.getTno()));
        query.where(todoEntity.writer.eq(requestRemoveTodoDTO.getWriter()));

        return query.fetch().get(0);
    }
}
