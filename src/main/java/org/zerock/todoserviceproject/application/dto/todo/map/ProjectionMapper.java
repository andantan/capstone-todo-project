package org.zerock.todoserviceproject.application.dto.todo.map;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.archive.ArchiveTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;
import org.zerock.todoserviceproject.domain.entity.archive.ArchiveTodoEntity;

@Component
@RequiredArgsConstructor
public class ProjectionMapper {

    private final ModelMapper modelMapper;

    public TodoDTO mapToDTO(TodoEntity todoEntity) {
        return this.modelMapper.map(todoEntity, TodoDTO.class);
    }

    public ArchiveTodoDTO mapToDTO(ArchiveTodoEntity archiveTodoEntity) {
        return this.modelMapper.map(archiveTodoEntity, ArchiveTodoDTO.class);
    }

    public TodoEntity mapToEntity(TodoDTO todoDTO) {
        return this.modelMapper.map(todoDTO, TodoEntity.class);
    }

    public ArchiveTodoEntity mapToEntity(ArchiveTodoDTO archiveTodoDTO) {
        return this.modelMapper.map(archiveTodoDTO, ArchiveTodoEntity.class);
    }

    public ArchiveTodoDTO convertToArchive(TodoDTO targetTodoDTO, Integer delta) {

        return ArchiveTodoDTO.builder()
                .tno(targetTodoDTO.getTno())
                .writer(targetTodoDTO.getWriter())
                .title(targetTodoDTO.getTitle())
                .date(targetTodoDTO.getDate())
                .from(targetTodoDTO.getFrom())
                .to(targetTodoDTO.getTo())
                .complete(targetTodoDTO.isComplete())
                .delta(delta)
                .build();

    }
}
