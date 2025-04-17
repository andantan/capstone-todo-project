package org.zerock.todoserviceproject.application.dto.todo.map;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseModifyTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseRegisterTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseMapper {

    public Map<String, String> getResponseMap(
            ResponseRegisterTodoDTO responseRegisterTodoDTO, String execution, String status
    ) {

        Map<String, String> responseMap =  this.getDefaultResponseMap(
                responseRegisterTodoDTO.getTno(),
                responseRegisterTodoDTO.getTitle(),
                responseRegisterTodoDTO.getWriter(),
                responseRegisterTodoDTO.getDueDate(),
                responseRegisterTodoDTO.isComplete()
        );

        Map<String, String> conditionResponseMap = this.getConditionResponseMap(
                execution, status
        );

        responseMap.putAll(conditionResponseMap);

        return responseMap;
    }


    public Map<String, String> getResponseMap(
            ResponseQueryTodoDTO responseQueryTodoDTO, String execution, String status
    ) {

        Map<String, String> responseMap =  this.getDefaultResponseMap(
                responseQueryTodoDTO.getTno(),
                responseQueryTodoDTO.getTitle(),
                responseQueryTodoDTO.getWriter(),
                responseQueryTodoDTO.getDueDate(),
                responseQueryTodoDTO.isComplete()
        );

        Map<String, String> conditionResponseMap = this.getConditionResponseMap(
                execution, status
        );

        responseMap.putAll(conditionResponseMap);

        return responseMap;
    }


    public List<Map<String, Object>> getResponseMap(
            List<ResponseQueryTodoDTO> responseQueryTodoDTOList
    ) {
        int total = responseQueryTodoDTOList.size();
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (int index = 0; index < total; index++) {
            Map<String, Object> responseMap = new HashMap<>();

            responseMap.put("index", index + 1);
            responseMap.put("content", this.getResponseMap(
                    responseQueryTodoDTOList.get(index), "query", "success"
            ));

            mapList.add(responseMap);
        }

        return mapList;
    }


    public Map<String, String> getResponseMap(
            ResponseModifyTodoDTO responseModifyTodoDTO, String execution, String status
    ) {
        Map<String, String> responseMap = this.getDefaultResponseMap(
                responseModifyTodoDTO.getTno(),
                responseModifyTodoDTO.getTitle(),
                responseModifyTodoDTO.getWriter(),
                responseModifyTodoDTO.getDueDate(),
                responseModifyTodoDTO.isComplete()
        );

        responseMap.put("mod_date", responseModifyTodoDTO.getModDate().toString());

        Map<String, String> conditionResponseMap = this.getConditionResponseMap(
                execution, status
        );

        responseMap.putAll(conditionResponseMap);

        return responseMap;
    }


    private Map<String, String> getDefaultResponseMap(
            @Nullable Long tno,
            @Nullable String title,
            @Nullable String writer,
            @Nullable LocalDate dueDate,
            @NotNull boolean iscompleted
    ) {
        Map<String, String> map = new HashMap<>();

        if (tno != null) {
            map.put("tno", String.valueOf(tno));
        }

        if (title != null) {
            map.put("title", title);
        }

        if (writer != null) {
            map.put("writer", writer);
        }

        if (dueDate != null) {
            map.put("due_date", dueDate.toString());
        }

        map.put("complete", iscompleted ? "true" : "false");

        return map;
    }

    private Map<String, String> getConditionResponseMap(
            @NotNull String execution,
            @NotNull String status
    ) {
        Map<String, String> map = new HashMap<>();

        map.put("execution", execution);
        map.put("status", status);

        return map;
    }


    public ResponseRegisterTodoDTO mapToRegisterResponseTodoDTO(TodoDTO todoDTO) {
        return ResponseRegisterTodoDTO.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .complete(todoDTO.isComplete())
                .build();
    }


    public ResponseQueryTodoDTO mapToQueryResponseTodoDTO(TodoDTO todoDTO) {
        return ResponseQueryTodoDTO.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .complete(todoDTO.isComplete())
                .build();
    }


    public ResponseModifyTodoDTO mapToModifyResponseTodoDTO(TodoDTO todoDTO) {
        return ResponseModifyTodoDTO.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .complete(todoDTO.isComplete())
                .modDate(todoDTO.getModDate())
                .build();
    }
}
