package org.zerock.todoserviceproject.domain.repository.todoArchive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.todoserviceproject.domain.entity.archive.TodoArchiveEntity;
import org.zerock.todoserviceproject.domain.repository.todoArchive.extension.query.TodoArchiveRepositoryQueryExtension;


@Repository
public interface TodoArchiveRepository
        extends JpaRepository<TodoArchiveEntity, Long>,
        TodoArchiveRepositoryQueryExtension {
}
