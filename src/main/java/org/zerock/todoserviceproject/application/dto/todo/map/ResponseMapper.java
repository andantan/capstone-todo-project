package org.zerock.todoserviceproject.application.dto.todo.map;


import org.springframework.stereotype.Component;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseModifyTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseMapper {

    public Map<String, String> getResponseMap(TodoEntity todoEntity) {

        return this.getDefaultResponseMap(
                todoEntity.getTno(),
                todoEntity.getTitle(),
                todoEntity.getWriter(),
                todoEntity.getDueDate(),
                todoEntity.isComplete()
        );

    }

    public Map<String, String> getResponseMap(TodoDTO todoDTO) {

        return this.getDefaultResponseMap(
                todoDTO.getTno(),
                todoDTO.getTitle(),
                todoDTO.getWriter(),
                todoDTO.getDueDate(),
                todoDTO.isComplete()
        );
    }


    public Map<String, String> getResponseMap(ResponseQueryTodoDTO responseQueryTodoDTO) {

        return this.getDefaultResponseMap(
                null,
                responseQueryTodoDTO.getTitle(),
                responseQueryTodoDTO.getWriter(),
                responseQueryTodoDTO.getDueDate(),
                responseQueryTodoDTO.isComplete()
        );
    }


    public Map<String, String> getResponseMap(ResponseModifyTodoDTO responseModifyTodoDTO) {
        Map<String, String> map = this.getDefaultResponseMap(
                responseModifyTodoDTO.getTno(),
                responseModifyTodoDTO.getTitle(),
                responseModifyTodoDTO.getWriter(),
                responseModifyTodoDTO.getDueDate(),
                responseModifyTodoDTO.isComplete()
        );

        map.put("mod_date", responseModifyTodoDTO.getModDate().toString());

        return map;
    }


    private Map<String, String> getDefaultResponseMap(
            Long tno, String title, String writer, LocalDate dueDate, boolean iscompleted
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

        if (iscompleted) {
            map.put("is_completed", "true");
        }


        return map;
    }


    public ResponseQueryTodoDTO mapToQueryResponseTodoDTO(TodoDTO todoDTO) {
        return ResponseQueryTodoDTO.builder()
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .complete(todoDTO.isComplete())
                .build();
    }


    public ResponseModifyTodoDTO mapToModifyResponseTodoDTO(TodoDTO todoDTO) {
        return ResponseModifyTodoDTO.builder()
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .dueDate(todoDTO.getDueDate())
                .complete(todoDTO.isComplete())
                .modDate(todoDTO.getModDate())
                .build();
    }
}
