package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZKServiceController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "这是一个通过zookeeper注册的服务";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Integer id) {
        User user = new User("scott");
        user.setId(id);
        return user;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "scott");
        User user2 = new User(2, "ameizi");
        User user3 = new User(3, "stev");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
}
