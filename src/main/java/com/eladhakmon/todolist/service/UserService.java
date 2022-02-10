package com.eladhakmon.todolist.service;

import com.eladhakmon.todolist.model.Task;
import com.eladhakmon.todolist.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ClientService {

    public void createUser(User user) {
        userDB.createUser(user);
    }


    public List<User> getAllUsers() {
        return userDB.getAllUsers();
    }


    public Optional<User> findUserById(Long id) {
        return userDB.findUserById(id);
    }


    public void updateUserById(User user, Long id) {
        try {
            List<User> users = userDB.getAllUsers();
            for (User u : users) {
                if (u.getId() == id) {
                    userDB.updateUserById(user);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteUserById(Long id) {
        userDB.deleteUserById(id);
    }

    public List<Task> getAllUserTasks(Long userId) {

        List<Task> tasks = userDB.findUserById(userId).get().getTasks();
        if (tasks != null) {
            return tasks;
        }
        System.out.println("for user with id: " + userId + " not found any tasks");
        return null;
    }


    @Override
    public boolean login(String username, String password) {
        List<User> users = userDB.getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println("User: " + username + " Logged successfully!");
                return true;
            }
        }
        System.out.println("Wrong Details...");
        return false;
    }
}
