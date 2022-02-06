package com.eladhakmon.todolist.service;

import com.eladhakmon.todolist.db.TaskDB;
import com.eladhakmon.todolist.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//todo - adding interface implementation
@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskDB taskDB;

    public void createTask(Task task) {
        taskDB.createTask(task);
    }


    public List<Task> getAllTasks() {
        return taskDB.getAllTasks();
    }


    public Optional<Task> findTaskById(Long id) {
        return taskDB.findTaskById(id);
    }


    public void updateTaskById(Task task, Long id) {
        taskDB.findTaskById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    taskToUpdate.setDeadLine(task.getDeadLine());
                    taskDB.updateTaskById(taskToUpdate);
                    return ResponseEntity.ok().body(taskToUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteById(Long id) {
        taskDB.deleteTaskById(id);
    }


}
