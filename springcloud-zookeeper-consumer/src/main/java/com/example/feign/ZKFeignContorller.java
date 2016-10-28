package com.example.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZKFeignContorller {

    @Autowired
    private DiscoveryClient discovery;

    @Autowired
    private ZKFeignHystrixClient zkFeignHystrixClient;

    @RequestMapping("/feignInfo")
    public ServiceInstance getInfo() {
        return discovery.getLocalServiceInstance();
    }

    @RequestMapping("/feign")
    public String feign() {
        return zkFeignHystrixClient.getInfo();
    }

}
