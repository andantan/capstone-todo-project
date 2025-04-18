package org.zerock.todoserviceproject.application.controller.module;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todoserviceproject.application.controller.AbstractTodoController;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestQueryTodoDTO;
import org.zerock.todoserviceproject.domain.service.module.query.TodoQueryService;

import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@Log4j2
@RequiredArgsConstructor
public final class TodoQueryController extends AbstractTodoController {

    private final TodoQueryService todoQueryService;


    @Operation(
            summary = "Todo 조회",
            description = "ResponseQueryTodoDTO 형식에 따름"
    )
    @GetMapping(
            value = "/query"
    )
    public Map<String, Object> query(
            @NotNull @Param("writer") String writer,
            @NotNull @Param("date") LocalDate date
    ) throws NoSuchElementException {

        return this.todoQueryService.requestQueryTodoList(
                RequestQueryTodoDTO.builder().writer(writer).date(date).build()
        );
    }
}
