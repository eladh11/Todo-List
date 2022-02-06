package com.eladhakmon.todolist.db;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskDB {

    @Autowired
    private TaskRepository taskRepository;


    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void updateTaskById(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


}
