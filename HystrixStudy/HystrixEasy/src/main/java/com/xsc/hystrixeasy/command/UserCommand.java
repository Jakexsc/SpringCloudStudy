package com.xsc.hystrixeasy.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xsc.hystrixeasy.service.UserService;
import commons.User;

import java.util.List;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 0:22
 */
public class UserCommand extends HystrixCommand<List<User>> {
    final List<Integer> ids;
    final UserService userService;

    /**
     * @param ids         id集合
     * @param userService user业务逻辑层
     */
    protected UserCommand(List<Integer> ids, UserService userService) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserCommand")));
        this.ids = ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUserById(ids);
    }
}
