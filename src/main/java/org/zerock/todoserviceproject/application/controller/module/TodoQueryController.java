package org.zerock.todoserviceproject.application.controller.module;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todoserviceproject.application.controller.AbstractTodoController;
import org.zerock.todoserviceproject.application.controller.util.TodoControllerUtil;
import org.zerock.todoserviceproject.domain.service.module.query.TodoQueryService;

import java.util.List;
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
    public Map<String, String> query(
            @NotNull @Param("tno") Long tno
    ) throws NoSuchElementException {

        return this.todoQueryService.requestQueryTodo(tno);
    }


    @Operation(
            summary = "Todo 조회 ( todo List 조회 )",
            description = "ResponseQueryTodoDTO 형식에 따름"
    )
    @GetMapping(
            value = "/query/list"
    )
    public List<Map<String, Object>> findAllTodoList (
            @NotNull @Param("sort") String sort,     //  [ desc | asc ]
            @Nullable @Param("limit") Integer limit
    ) throws Exception {

        boolean desc = TodoControllerUtil.isDescendingSorting(sort);

        return (limit == null)
                ? this.todoQueryService.requestQueryTodoList(desc)
                : this.todoQueryService.requestQueryTodoList(desc, limit);
    }

}
