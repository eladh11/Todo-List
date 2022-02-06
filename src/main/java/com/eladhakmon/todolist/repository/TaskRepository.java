package com.eladhakmon.todolist.repository;

import com.eladhakmon.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
