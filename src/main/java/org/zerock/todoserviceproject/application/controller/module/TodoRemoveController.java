package org.zerock.todoserviceproject.application.controller.module;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todoserviceproject.domain.service.module.remove.TodoRemoveService;

import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@Log4j2
@RequiredArgsConstructor
public class TodoRemoveController {

    private final TodoRemoveService todoRemoveService;


    @Operation(
            summary = "Todo 삭제 ( PK := tno )",
            description = "DELETE BY TNO"
    )
    @DeleteMapping(
            value = "/remove"
    )
    public Map<String, String> remove(
            @Valid @Param("tno") Long tno
    ) throws NoSuchElementException {

        return this.todoRemoveService.requestRemove(tno);
    }
}
