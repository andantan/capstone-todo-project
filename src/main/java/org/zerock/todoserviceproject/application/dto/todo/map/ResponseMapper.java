package org.zerock.todoserviceproject.application.dto.todo.map;


import org.springframework.stereotype.Component;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
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


    private Map<String, String> getDefaultResponseMap(
            Long tno, String title, String writer, LocalDate dueDate, boolean iscompleted
    ) {
        Map<String, String> map = new HashMap<>();

        map.put("tno", String.valueOf(tno));
        map.put("title", title);
        map.put("writer", writer);
        map.put("dueDate", dueDate.toString());
        map.put("iscompleted", String.valueOf(iscompleted));

        return map;
    }
}
