package com.eladhakmon.todolist.service;

import com.eladhakmon.todolist.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    @Autowired
    protected UserDB userDB;

    public abstract boolean login(String email, String password);


}
