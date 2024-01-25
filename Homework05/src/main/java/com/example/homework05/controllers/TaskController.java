package com.example.homework05.controllers;

import com.example.homework05.domain.Task;
import com.example.homework05.domain.TaskStatus;
import com.example.homework05.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.add(new Task(task));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.findTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateById(id, new Task(task));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
