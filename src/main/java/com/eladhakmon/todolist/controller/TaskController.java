package com.eladhakmon.todolist.controller;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/tasks")
@Slf4j
public class TaskController {


    @Autowired
    TaskService taskService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        log.info("Create new Task [{}]", task);
        taskService.createTask(task);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("List of all Tasks");
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {
        log.info("find Task by id [{}]", id);
        return taskService.findTaskById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateTaskById(@RequestBody Task task, @PathVariable(value = "id") Long id) {
        log.info("update a Task by id [{}], new information is: [{}]", id, task);
        return taskService.findTaskById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    taskToUpdate.setDeadLine(task.getDeadLine());
                    taskService.updateTaskById(taskToUpdate, id);
                    return ResponseEntity.ok().body(taskToUpdate);
                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTaskById(@PathVariable(value = "id") Long id) {
        log.info("Deleting Task with id [{}]", id);
        return taskService.findTaskById(id)
                .map(taskToDelete -> {
                    taskService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
