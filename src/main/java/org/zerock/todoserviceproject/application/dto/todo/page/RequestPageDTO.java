package org.zerock.todoserviceproject.application.dto.todo.page;


import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPageDTO {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String keyword;
    private String writer;
    private String link;

    private LocalDate from;
    private LocalDate to;

    private Boolean completed;
    private Boolean desc;

    public Pageable getPageable(String column) {
        PageRequest pageRequest = PageRequest.of(this.page - 1, this.size);

        return this.desc ? pageRequest.withSort(Sort.by(column).descending()) : pageRequest;
    }

}
