package org.zerock.todoserviceproject.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.todoserviceproject.domain.entity.archive.ArchiveTodoEntity;


@Repository
public interface ArchiveTodoRepository extends JpaRepository<ArchiveTodoEntity, Long> {
}
