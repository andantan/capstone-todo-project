package org.zerock.todoserviceproject.application.controller.module;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todoserviceproject.application.controller.AbstractTodoController;
import org.zerock.todoserviceproject.domain.service.module.query.TodoQueryService;

import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@Log4j2
@RequiredArgsConstructor
public final class TodoQueryController extends AbstractTodoController {

    private final TodoQueryService todoQueryService;


    @Operation(
            summary = "Todo 조회",
            description = "ResponseBodyTodoDTO 형식에 따름"
    )
    @GetMapping(
            value = "/query/{tno}"
    )
    public Map<String, String> find(
            @PathVariable("tno") Long tno
    ) throws NoSuchElementException {

        return this.todoQueryService.requestFindTodo(tno);
    }

}
