package com.example.homework05.repositoryes;

import com.example.homework05.domain.Task;
import com.example.homework05.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus(TaskStatus status);

    @Modifying
    @Query("update Task u set u.description = ?1, u.status = ?2 where u.id = ?3")
    void updateTaskById(String description, TaskStatus status, Long id);
}
