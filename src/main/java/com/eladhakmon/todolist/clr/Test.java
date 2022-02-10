package com.eladhakmon.todolist.clr;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.model.User;
import com.eladhakmon.todolist.repository.TaskRepository;
import com.eladhakmon.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Order(1)
public class Test implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        createTasks();
        createUsers();
    }

    //todo - internal factor util
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


    public void createUsers() {
        User u1 = User.builder().username("elad").password("elad1234").build();
        userRepository.save(u1);

        LocalDateTime time = LocalDateTime.now();
        Task t1 = Task.builder().title("Task for elad").description("stam description...")
                .deadLine(convertUtilDateToSQL(new Date(time.getYear() + 1, time.getMonthValue()
                        , time.getDayOfMonth()))).build();

        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        u1.setTasks(tasks);
        userRepository.saveAndFlush(u1);

        User u2 = User.builder().username("natali").password("natali1234").build();
        userRepository.save(u2);

        User u3 = User.builder().username("kobi").password("kobi1234").build();
        userRepository.save(u3);

    }

}
