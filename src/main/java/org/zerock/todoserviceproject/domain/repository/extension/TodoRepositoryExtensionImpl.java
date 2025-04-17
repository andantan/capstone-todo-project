package org.zerock.todoserviceproject.domain.repository.extension;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.todoserviceproject.application.dto.todo.page.RequestPageDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;
import org.zerock.todoserviceproject.domain.entity.QTodoEntity;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.util.List;
import java.util.Objects;


public class TodoRepositoryExtensionImpl extends QuerydslRepositorySupport
        implements TodoRepositoryExtension {

    public TodoRepositoryExtensionImpl() {
        super(TodoEntity.class);
    }

    @Override
    public Page<ResponseQueryTodoDTO> requestFindAll(RequestPageDTO requestPageDTO) {
        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        JPQLQuery<TodoEntity> query = from(todoEntity);

        if (requestPageDTO.getFrom() != null) {
            query.where(todoEntity.dueDate.goe(requestPageDTO.getFrom()));
        }

        if (requestPageDTO.getTo() != null) {
            query.where(todoEntity.dueDate.loe(requestPageDTO.getTo()));
        }

        if (requestPageDTO.getCompleted() != null) {
            query.where(todoEntity.complete.eq(requestPageDTO.getCompleted()));
        }

        if (requestPageDTO.getKeyword() != null) {
            query.where(todoEntity.title.contains(requestPageDTO.getKeyword()));
        }

        if (requestPageDTO.getWriter() != null) {
            query.where(todoEntity.writer.eq(requestPageDTO.getWriter()));
        }

        Objects.requireNonNull(this.getQuerydsl()).applyPagination(
                requestPageDTO.getPageable("tno"), query
        );

        JPQLQuery<ResponseQueryTodoDTO> dtoQuery = query.select(
                Projections.bean(
                        ResponseQueryTodoDTO.class,
                        todoEntity.tno,
                        todoEntity.title,
                        todoEntity.writer,
                        todoEntity.dueDate,
                        todoEntity.complete
                )
        );

        List<ResponseQueryTodoDTO> quriedList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();

        return new PageImpl<>(quriedList, requestPageDTO.getPageable("tno"), total);

    }
}
