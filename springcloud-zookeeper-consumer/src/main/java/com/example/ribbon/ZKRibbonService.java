package com.example.ribbon;

import com.example.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 带hystrix熔断机制和ribbon负载均衡机制的Service
 */
@Service
@SuppressWarnings("unchecked")
public class ZKRibbonService {

    private static final Logger loger = Logger.getLogger(ZKRibbonService.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用集群方法获取信息
     *
     * @return 字符串类型的信息
     */
    @HystrixCommand(fallbackMethod = "fallBack")
    public String welcome() {
        return restTemplate.getForObject("http://zk-service", String.class);
    }

    @HystrixCommand(fallbackMethod = "fallfindByIdBack")
    public User findById(Integer id) {
        return restTemplate.getForObject("http://zk-service/" + id, User.class);
    }

    @HystrixCommand(fallbackMethod = "fallfindAllBack")
    public List<User> findAll() {
        // wrong 此处使用List.class其实返回的并不真正意义上的List<User>而是ArrayList<LinkedHashMap>
//        List<User> userList = this.restTemplate.getForObject("http://zk-service/findAll", List.class);
//        for (User user : userList) {
//            System.out.println(user.getAddress());
//        }

        // right 切记此处需要使用Object[]即对象数组来接收
        User[] users = this.restTemplate.getForObject("http://zk-service/findAll", User[].class);
        List<User> list = Arrays.asList(users);
        return list;
    }

    /**
     * 当熔断发生时getInfo方法执行该方法进行处理
     *
     * @return 返回错误的返回结果
     */
    private String fallBack() {
        loger.warn("调用服务失败，短路器执行");
        return "调用服务失败，短路器执行";
    }

    /**
     * 当熔断发生时findById方法执行该方法进行处理
     *
     * @return 返回错误的返回结果
     */
    private User fallfindByIdBack(Integer id) {
        loger.warn("调用服务失败，短路器执行");
        return new User(id, "调用服务失败，短路器执行");
    }

    /**
     * 当熔断发生时findAll方法执行该方法进行处理
     *
     * @return 返回错误的返回结果
     */
    private List<User> fallfindAllBack() {
        loger.warn("调用服务失败，短路器执行");
        return Collections.emptyList();
    }

}
