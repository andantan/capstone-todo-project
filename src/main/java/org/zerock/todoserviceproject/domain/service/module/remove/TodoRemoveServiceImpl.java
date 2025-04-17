package org.zerock.todoserviceproject.domain.service.module.remove;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.todoserviceproject.domain.repository.TodoRepository;

import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoRemoveServiceImpl implements TodoRemoveService {

    private final TodoRepository todoRepository;


    @Override
    public Map<String, String> requestRemove(Long tno) {
        this.todoRepository.deleteById(tno);

        return Map.of(
                "status", "success",
                "execution", "remove",
                "tno", tno.toString()
        );
    }
}
