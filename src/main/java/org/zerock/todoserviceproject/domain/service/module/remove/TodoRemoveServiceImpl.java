package org.zerock.todoserviceproject.domain.service.module.remove;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.application.dto.todo.TodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.archive.ArchiveTodoDTO;
import org.zerock.todoserviceproject.application.dto.todo.map.ProjectionMapper;
import org.zerock.todoserviceproject.application.dto.todo.projection.request.RequestRemoveTodoDTO;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;
import org.zerock.todoserviceproject.domain.repository.ArchiveTodoRepository;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoRemoveServiceImpl implements TodoRemoveService {

    private final TodoRepository todoRepository;
    private final ArchiveTodoRepository archiveTodoRepository;
    private final ProjectionMapper projectionMapper;


    @Override
    public Map<String, String> requestRemove(RequestRemoveTodoDTO requestRemoveTodoDTO) {

        TodoDTO archiveTargetTodoDTO = this.projectionMapper.mapToDTO(
                this.retriveTupleAndRemove(requestRemoveTodoDTO)
        );

        ArchiveTodoDTO archiveTodoDTO = this.projectionMapper.convertToArchive(
                archiveTargetTodoDTO, requestRemoveTodoDTO.getDelta()
        );

        this.archiveTodoRepository.save(
                this.projectionMapper.mapToEntity(archiveTodoDTO)
        );

        return Map.of(
                "status", "success",
                "execution", "remove",
                "tno", requestRemoveTodoDTO.getTno().toString(),
                "writer", requestRemoveTodoDTO.getWriter()
        );
    }

    private TodoEntity retriveTupleAndRemove(RequestRemoveTodoDTO requestRemoveTodoDTO) {
        TodoEntity targetEntity = this.todoRepository.findRemoveTarget(requestRemoveTodoDTO);

        this.todoRepository.deleteById(requestRemoveTodoDTO.getTno());

        return targetEntity;
    }
}
