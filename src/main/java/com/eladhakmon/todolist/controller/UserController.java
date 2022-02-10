package com.eladhakmon.todolist.controller;


import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.model.User;
import com.eladhakmon.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/users")
@Slf4j
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        log.info("Create new User [{}]", user);
        userService.createUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("List of all Users");
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {
        log.info("find User by id [{}]", id);
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable(value = "id") Long id) {
        log.info("update a User by id [{}], new information is: [{}]", id, user);
        return userService.findUserById(id).map(userToUpdate -> {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setTasks(user.getTasks());
            userService.updateUserById(userToUpdate, id);
            return ResponseEntity.ok().body(userToUpdate);
        }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long id) {
        log.info("Deleting User with id [{}]", id);
        return userService.findUserById(id).map(userToDelete -> {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> getAllUserTask(@PathVariable(value = "id") Long id) {
        log.info("List of all Tasks Associated to user with id:[{}]", id);
        return new ResponseEntity<List<Task>>(userService.getAllUserTasks(id), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        if (userService.login(username, password)) {
            System.out.println("User login Successfully!");
            return new ResponseEntity<User>(HttpStatus.CREATED);
        }
        System.out.println("the details are incorrect...");
        return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);
    }

}
