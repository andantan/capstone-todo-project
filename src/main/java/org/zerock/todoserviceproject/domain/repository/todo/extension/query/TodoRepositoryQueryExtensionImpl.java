package org.zerock.todoserviceproject.domain.repository.todo.extension.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestQueryTodoDTO;
import org.zerock.todoserviceproject.domain.entity.QTodoEntity;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TodoRepositoryQueryExtensionImpl extends QuerydslRepositorySupport
        implements TodoRepositoryQueryExtension {

    public TodoRepositoryQueryExtensionImpl() {
        super(TodoEntity.class);
    }

    @Override
    public List<TodoEntity> findListByDate(RequestQueryTodoDTO requestQueryTodoDTO) {
        LocalDateTime startOfDay = requestQueryTodoDTO.getDate().atStartOfDay();
        LocalDateTime endOfDay = requestQueryTodoDTO.getDate().atTime(23, 59);

        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        JPQLQuery<TodoEntity> query = from(todoEntity);

        BooleanBuilder dateCondition = buildDateCondition(todoEntity, startOfDay, endOfDay);

        query.where(todoEntity.writer.eq(requestQueryTodoDTO.getWriter()));
        query.where(dateCondition);

        return query.fetch();
    }

    private BooleanBuilder buildDateCondition(QTodoEntity todoEntity, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        BooleanBuilder dateBuilder = new BooleanBuilder();
        BooleanBuilder fromCondition = new BooleanBuilder();
        BooleanBuilder toCondition = new BooleanBuilder();

        fromCondition.and(todoEntity.from.goe(startOfDay))
                .and(todoEntity.from.loe(endOfDay));

        toCondition.and(todoEntity.to.goe(startOfDay))
                .and(todoEntity.to.loe(endOfDay));

        dateBuilder.or(fromCondition).or(toCondition);

        return dateBuilder;
    }
}
