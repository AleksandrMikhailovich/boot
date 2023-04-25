package ru.spring.boot.bootfinalapp.service;

import ru.spring.boot.bootfinalapp.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    void deleteUser(int id);
}
