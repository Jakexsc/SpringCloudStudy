package com.xsc.eurekaprovider.controller;

import commons.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 0:09
 */
@RestController
public class UserController {
    @GetMapping("/user/{ids}")
    public List<User> users (@PathVariable String ids) {
        System.out.println(ids);
        String[] split = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s : split) {
            User user = new User();
            user.setId(Integer.valueOf(s));
            users.add(user);
        }
        return users;
    }
}
