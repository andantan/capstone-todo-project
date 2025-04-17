package org.zerock.todoserviceproject.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.todoserviceproject.domain.entity.TodoEntity;
import org.zerock.todoserviceproject.domain.repository.extension.TodoRepositoryExtension;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoRepositoryExtension {
}
