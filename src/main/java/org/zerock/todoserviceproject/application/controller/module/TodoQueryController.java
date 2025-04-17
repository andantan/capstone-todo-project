package org.zerock.todoserviceproject.application.controller.module;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todoserviceproject.application.controller.AbstractTodoController;
import org.zerock.todoserviceproject.application.controller.util.TodoControllerUtil;
import org.zerock.todoserviceproject.application.dto.todo.page.RequestFilterDTO;
import org.zerock.todoserviceproject.domain.service.module.query.TodoQueryService;

import java.time.LocalDate;
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
    public List<Map<String, Object>> queryList (
            @NotNull @Param("sort") String sort,     //  [ desc | asc ]
            @Nullable @Param("limit") Integer limit
    ) throws Exception {

        boolean desc = TodoControllerUtil.isDescendingSorting(sort);

        return (limit == null)
                ? this.todoQueryService.requestQueryTodoList(desc)
                : this.todoQueryService.requestQueryTodoList(desc, limit);
    }


    @Operation(
            summary = "Todo 조회 ( Paginated list method )",
            description = "ResponseQueryTodoDTO 형식에 따름"
    )
    @GetMapping(
            value = "/query/list/pagination"
    )
    public List<Map<String, Object>> findPaginatedList (
            @NotNull @Param("sort") String sort,    //  [ desc | asc ]
            @NotNull @Param("page") Integer page,
            @NotNull @Param("size") Integer size
    ) throws Exception {

        boolean desc = TodoControllerUtil.isDescendingSorting(sort);

        return this.todoQueryService.requestQueryPaginatedTodoList(desc, page, size);
    }

    @Operation(
            summary = "Todo 조회 ( With filter )",
            description = "조건(키워드, 작성자, from-to, 완료여부)에 따른 조회 (Not paginated)"
    )
    @GetMapping(
            value = "/query/list/pagination/filter"
    )
    public List<Map<String, Object>> findListWithFilter(
            @NotNull @Param("sort") String sort,
            @NotNull @Param("page") Integer page,
            @NotNull @Param("size") Integer size,
            @Nullable @Param("keyword") String keyword,
            @Nullable @Param("writer") String writer,
            @Nullable @Param("from") LocalDate from,
            @Nullable @Param("to") LocalDate to,
            @Nullable @Param("completed") Boolean completed
    ) throws Exception {

        boolean desc = TodoControllerUtil.isDescendingSorting(sort);

        RequestFilterDTO requestFilterDTO = RequestFilterDTO.builder()
                .keyword(keyword)
                .writer(writer)
                .from(from)
                .to(to)
                .completed(completed)
                .build();

        return this.todoQueryService.requestQueryFilteredTodoList(desc, page, size, requestFilterDTO);
    }
}
