package com.task.api_task.controller;

import com.task.api_task.model.Task;
import com.task.api_task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("{id}")
    Task updateTask(@PathVariable String id,@RequestBody Task task){
        Task taskFound = taskRepository.findById(id).orElseThrow(RuntimeException::new);

        taskFound.setName(task.getName());
        taskFound.setCompleted(task.isCompleted());

        return taskRepository.save(taskFound);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteTask(@PathVariable String id){
        Task taskFound = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        taskRepository.delete(taskFound);
    }
}

























