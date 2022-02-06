package com.eladhakmon.todolist.clr;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    //todo - instarnal factor util
    public void createTasks() {

        LocalDateTime time = LocalDateTime.now();
        Task t1 = Task.builder().title("first Task 1").description("first description...")
                .deadLine(convertUtilDateToSQL(new Date(time.getYear() + 1, time.getMonthValue()
                        , time.getDayOfMonth()))).build();

        taskRepository.save(t1);


        Task t2 = Task.builder().title("seconde Task 2").description("seconde description...")
                .deadLine(convertUtilDateToSQL(new Date(time.getYear() + 1, time.getMonthValue(),
                        time.getDayOfMonth()))).build();


        taskRepository.save(t2);


        Task t3 = Task.builder().title("third Task 3").description("third description...")
                .deadLine(convertUtilDateToSQL(new Date(time.getYear() + 1, time.getMonthValue(),
                        time.getDayOfMonth()))).build();

        taskRepository.save(t3);

    }

    //todo - remove to util
    public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
        return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
    }

}
