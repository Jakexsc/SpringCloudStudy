package com.xsc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/14 23:18
 */
@Component
public class PermissionFilter extends ZuulFilter {
    /**
     * 过滤器的类型
     *
     * @return String
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的优先级
     *
     * @return int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤
     *
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心的过滤逻辑
     *
     * @return Object 无所谓
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!"xsc".equals(username) || !"chen2964".equals(password)) {
            // 设置服务器响应为false
            currentContext.setSendZuulResponse(false);
            // 设置状态码为401
            currentContext.setResponseStatusCode(401);
            // 设置响应头
            currentContext.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            currentContext.setResponseBody("非法访问!");
        }
        return null;
    }
}
