package com.eladhakmon.todolist.service;

import com.eladhakmon.todolist.db.TaskDB;
import com.eladhakmon.todolist.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    protected TaskDB taskDB;

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
        try {
            List<Task> tasks = taskDB.getAllTasks();
            for (Task t : tasks) {
                if (t.getId() == id) {
                    taskDB.updateTaskById(task);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteById(Long id) {
        taskDB.deleteTaskById(id);
    }


}
