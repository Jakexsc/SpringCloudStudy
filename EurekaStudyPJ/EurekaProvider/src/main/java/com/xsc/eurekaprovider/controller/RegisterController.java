package com.xsc.eurekaprovider.controller;

import commons.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/2 22:40
 */
@Controller
public class RegisterController {
    @PostMapping("/register")
    public String register(User user) {
        return "redirect:http://eureka-provider/loginPage?name=" + user.getName();
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String name) {
        return "loginPage:" + name;
    }
}
