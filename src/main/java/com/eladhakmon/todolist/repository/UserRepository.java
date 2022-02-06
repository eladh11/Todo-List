package com.eladhakmon.todolist.repository;

import com.eladhakmon.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
