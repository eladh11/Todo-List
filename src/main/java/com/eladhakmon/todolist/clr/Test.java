package com.eladhakmon.todolist.clr;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Order(1)
public class Test implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void run(String... args) throws Exception {
        createTasks();
    }

    public void createTasks() {
        Task t1 = new Task();
        t1.setTitle("first Task 1");
        t1.setDescription("first description...");
        t1.setDeadLine(convertUtilDateToSQL(new Date(2023, 01, 01)));

        taskRepository.save(t1);
        Task t2 = new Task();
        t2.setTitle("seconde Task 1");
        t2.setDescription("seconde description...");
        t2.setDeadLine(convertUtilDateToSQL(new Date(2023, 01, 01)));

        taskRepository.save(t2);
        Task t3 = new Task();
        t3.setTitle("third Task 1");
        t3.setDescription("third description...");
        t3.setDeadLine(convertUtilDateToSQL(new Date(2023, 01, 01)));

        taskRepository.save(t3);

    }

    public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
        return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
    }

}
