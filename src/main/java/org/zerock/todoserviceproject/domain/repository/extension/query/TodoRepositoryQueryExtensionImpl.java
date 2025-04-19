package org.zerock.todoserviceproject.domain.repository.extension.query;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestQueryTodoDTO;
import org.zerock.todoserviceproject.domain.entity.QTodoEntity;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.util.List;


public class TodoRepositoryQueryExtensionImpl extends QuerydslRepositorySupport
        implements TodoRepositoryQueryExtension {

    public TodoRepositoryQueryExtensionImpl() {
        super(TodoEntity.class);
    }

    @Override
    public List<TodoEntity> findListByDate(RequestQueryTodoDTO requestQueryTodoDTO) {
        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        JPQLQuery<TodoEntity> query = from(todoEntity);

        query.where(todoEntity.writer.eq(requestQueryTodoDTO.getWriter()));
        query.where(todoEntity.date.eq(requestQueryTodoDTO.getDate()));

        return query.fetch();
    }
}
