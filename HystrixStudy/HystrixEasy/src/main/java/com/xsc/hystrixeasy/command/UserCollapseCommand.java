package com.xsc.hystrixeasy.command;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.xsc.hystrixeasy.service.UserService;
import commons.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 0:26
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Integer> {
    final Integer id;
    final UserService userService;

    /**
     * 构造器注入 防止依赖为空
     *
     * @param id
     * @param userService user业务逻辑层
     */
    public UserCollapseCommand(Integer id, UserService userService) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserCollapseCommand")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.id = id;
        this.userService = userService;
    }

    /**
     * 请求参数
     *
     * @return Integer
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并的方法
     *
     * @param collection 所有的请求
     * @return HystrixCommand<List < User>>
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {
        List<Integer> ids = new ArrayList<>();
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            ids.add(userIntegerCollapsedRequest.getArgument());
        }
        return new UserCommand(ids, userService);
    }

    /**
     * 请求结果分发
     *
     * @param users
     * @param collection 所有的请求
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            userIntegerCollapsedRequest.setResponse(users.get(count++));
        }

    }

}
