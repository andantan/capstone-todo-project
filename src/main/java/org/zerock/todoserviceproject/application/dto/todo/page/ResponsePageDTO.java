package org.zerock.todoserviceproject.application.dto.todo.page;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.zerock.todoserviceproject.application.dto.todo.map.ResponseMapper;
import org.zerock.todoserviceproject.application.dto.todo.projection.response.ResponseQueryTodoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@ToString
public class ResponsePageDTO {
    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<ResponseQueryTodoDTO> dtoList;

    @Builder(builderMethodName = "fullBuilder")
    public ResponsePageDTO(
            RequestPageDTO requestPageDTO,
            List<ResponseQueryTodoDTO> dtoList
    ) {
        if (dtoList.isEmpty()) { return; }

        this.page = requestPageDTO.getPage();
        this.size = requestPageDTO.getSize();

        this.total = dtoList.size();
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil((this.total/(double)size)));

        this.end = Math.min(end, last);

        this.prev = this.start > 1;
        this.next = this.total > this.end * this.size;
    }

    public List<Map<String, Object>> toMap() {
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (int index = 0; index < this.total; index++) {
            Map<String, Object> map = new HashMap<>();

            map.put(
                    "index", this.size * ( this.page - 1 ) + ( index + 1 )
            );  // 31 ~ 40 , 51 ~ 60
            map.put(
                    "content", this.getResponseMap(this.dtoList.get(index))
            );

            mapList.add(map);
        }

        return mapList;
    }

    private Map<String, String> getResponseMap(ResponseQueryTodoDTO responseQueryTodoDTO) {

        Map<String, String> responseMap = new HashMap<>();

        responseMap.put("tno", responseQueryTodoDTO.getTno().toString());
        responseMap.put("title", responseQueryTodoDTO.getTitle());
        responseMap.put("writer", responseQueryTodoDTO.getWriter());
        responseMap.put("due_date", responseQueryTodoDTO.getDueDate().toString());
        responseMap.put("complete", responseQueryTodoDTO.isComplete() ? "true" : "false");

        return responseMap;
    }
}
