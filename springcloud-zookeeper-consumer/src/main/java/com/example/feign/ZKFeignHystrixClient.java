package com.example.feign;

import com.example.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * 带hystrix熔断机制和feign负载均衡机制的Service
 */
@FeignClient(value = "zk-service", fallback = ZKFeignHystrixClient.ZKFeignHystrixClientFallback.class)
public interface ZKFeignHystrixClient {

    @RequestMapping(path = "/")
    String welcome();

    @RequestMapping(value = "/{id}")
    User findById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/findAll")
    List<User> findAll();

    @Component
    class ZKFeignHystrixClientFallback implements ZKFeignHystrixClient {

        private final Logger logger = LoggerFactory.getLogger(ZKFeignHystrixClientFallback.class);

        @Override
        public String welcome() {
            logger.info("调用服务失败，短路器执行");
            return "调用服务失败，短路器执行";
        }

        @Override
        public User findById(Integer id) {
            return new User(id, "调用服务失败，短路器执行");
        }

        @Override
        public List<User> findAll() {
            return Collections.emptyList();
        }
    }

}