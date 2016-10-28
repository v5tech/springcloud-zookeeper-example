package com.example;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    User saveUser(User user);

    List<User> findAll();
}
