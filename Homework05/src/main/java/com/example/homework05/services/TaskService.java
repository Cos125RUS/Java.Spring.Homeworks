package com.example.homework05.services;

import com.example.homework05.domain.Task;
import com.example.homework05.domain.TaskStatus;
import com.example.homework05.repositoryes.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task add(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findTasksByStatus(TaskStatus status){
        return taskRepository.findTasksByStatus(status);
    }

    @Transactional
    public Task updateById(Long id, Task task) {
        taskRepository.updateTaskById(task.getDescription(), task.getStatus(), id);
        task.setId(id);
        return task;
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
