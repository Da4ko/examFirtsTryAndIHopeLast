package com.example.exam.service;

import com.example.exam.model.service.UserServiceModel;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findUserByNameAndPassword(String username, String password);

    String findUserByName(UserServiceModel userServiceModel);
}
