package com.xsc.eurekaconsumer.controller;

import com.xsc.eurekaconsumer.common.Const;
import commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/30 23:41
 */
@RestController
public class UseHelloController {
    final DiscoveryClient discoveryClient;
    final RestTemplate restTemplate;

    /**
     * 构造器注入
     *
     * @param discoveryClient
     * @param restTemplate
     */
    @Autowired
    public UseHelloController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    /**
     * 采用写死的方法
     *
     * @return String
     */
    @GetMapping("/hello1")
    public String hello1() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:1113/hello");
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == Const.STATUS_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = bufferedReader.readLine();
                bufferedReader.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 采用DiscoveryClient根据服务名查询服务详细信息
     *
     * @return String
     */
    @GetMapping("/hello2")
    public String hello2() {
        //因为服务部署可能是集群化部署 所以是返回List
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        ServiceInstance serviceInstance = instances.get(0);
        //得到地址
        String host = serviceInstance.getHost();
        //得到端口
        int port = serviceInstance.getPort();
        StringBuffer stringBuffer = new StringBuffer();
        //进行拼接
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringBuffer.toString());
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == Const.STATUS_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = bufferedReader.readLine();
                bufferedReader.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 手动负载均衡 观察服务提供者集群化部署
     *
     * @return String
     */
    Integer count = 0;

    @GetMapping("/hello3")
    public String hello3() {
        //因为服务部署可能是集群化部署 所以是返回List
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        ServiceInstance serviceInstance = instances.get((count++) % instances.size());
        //得到地址
        String host = serviceInstance.getHost();
        //得到端口
        int port = serviceInstance.getPort();
        StringBuffer stringBuffer = new StringBuffer();
        //进行拼接
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringBuffer.toString());
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == Const.STATUS_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = bufferedReader.readLine();
                bufferedReader.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 利用RestTemplate优化http调用
     *
     * @return String
     */
    @GetMapping("/hello4")
    public String hello4() {
        //因为服务部署可能是集群化部署 所以是返回List
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        ServiceInstance serviceInstance = instances.get(0);
        //得到地址
        String host = serviceInstance.getHost();
        //得到端口
        int port = serviceInstance.getPort();
        StringBuffer stringBuffer = new StringBuffer();
        //进行拼接
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        String s = restTemplate.getForObject(stringBuffer.toString(), String.class);
        return s;
    }

    /**
     * 负载均衡 一行代码搞定
     *
     * @return String
     */
    @GetMapping("/hello5")
    public String hello5() {
        return restTemplate.getForObject("http://eureka-provider/hello", String.class);
    }

    /**
     * 测试两种方法
     */
    @GetMapping("/hello6")
    public void hello6() {
        String xsc1 = restTemplate.getForObject("http://eureka-provider/hello2?name={1}", String.class, "xsc");
        System.out.println(xsc1);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://eureka-provider/hello2", String.class, "xsc");
        HttpStatus statusCode = forEntity.getStatusCode();
        // 状态码并返回结果
        System.out.println("statusCode:" + statusCode);
        // 状态码
        int statusCodeValue = forEntity.getStatusCodeValue();
        System.out.println("statusCodeValue:" + statusCodeValue);
        String body = forEntity.getBody();
        // 接口内容
        System.out.println("body:" + body);
        HttpHeaders headers = forEntity.getHeaders();
        // 头部内容
        Set<String> headerSets = headers.keySet();
        for (String headerSet : headerSets) {
            System.out.println(headerSet + ":" + headers.get(headerSet));
        }
    }

    /**
     * 测试getForObject三种不同重载方法
     *
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/hello7")
    public void hello7() throws UnsupportedEncodingException {
        String xsc = restTemplate.getForObject("http://eureka-provider/hello2?name={1}", String.class, "xsc");
        System.out.println(xsc);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "javaxsc");
        String forObject = restTemplate.getForObject("http://eureka-provider/hello2?name={name}", String.class, map);
        System.out.println(forObject);
        //中文必须转为utf-8 不然报错
        String url = "http://eureka-provider/hello2?name=" + URLEncoder.encode("谢树琛", "utf-8");
        URI uri = URI.create(url);
        String forObject1 = restTemplate.getForObject(uri, String.class);
        System.out.println(forObject1);
    }

    @GetMapping("/hello8")
    public void hello8() {
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("id", "2");
        map.add("name", "xsc");
        map.add("password", "chen2964");
        User user1 = restTemplate.postForObject("http://eureka-provider/addUser1", map, User.class);
        System.out.println(user1);
        User user = new User();
        user.setId(1);
        user.setName("xsc");
        user.setPassword("chen2964");
        User user2 = restTemplate.postForObject("http://eureka-provider/addUser2", user, User.class);
        System.out.println(user2);
    }

    @GetMapping("/hello9")
    public void hello9() {
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("id", "2");
        map.add("name", "xsc");
        map.add("password", "chen2964");
        URI uri = restTemplate.postForLocation("http://eureka-provider/register", map);
        String forObject = restTemplate.getForObject(uri, String.class);
        System.out.println(forObject);
    }

    @GetMapping("/hello10")
    public void hello10() {
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("id", "2");
        map.add("name", "xsc");
        map.add("password", "chen2964");
        restTemplate.put("http://eureka-provider/updateUser1", map);
        User user = new User();
        user.setId(1);
        user.setName("xsc");
        user.setPassword("chen2964");
        restTemplate.put("http://eureka-provider/updateUser2", user);
    }

    @GetMapping("/hello11")
    public void hello11() {
        restTemplate.delete("http://eureka-provider/deleteUser1?id={1}", 99);
        restTemplate.delete("http://eureka-provider/deleteUser2/{id}", 98);
    }
}
