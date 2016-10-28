package com.example.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 带hystrix熔断机制和feign负载均衡机制的Service
 */
@FeignClient(value = "zk-service", fallback = ZKFeignHystrixClient.ZKFeignHystrixClientFallback.class)
public interface ZKFeignHystrixClient {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    String getInfo();

    @Component
    class ZKFeignHystrixClientFallback implements ZKFeignHystrixClient {

        private final Logger logger = LoggerFactory.getLogger(ZKFeignHystrixClientFallback.class);

        @Override
        public String getInfo() {
            logger.info("调用服务失败，短路器执行");
            return "调用服务失败，短路器执行";
        }
    }

}