package com.eladhakmon.todolist.db;

import com.eladhakmon.todolist.model.User;
import com.eladhakmon.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDB {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUserById(User user) {
        userRepository.saveAndFlush(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
