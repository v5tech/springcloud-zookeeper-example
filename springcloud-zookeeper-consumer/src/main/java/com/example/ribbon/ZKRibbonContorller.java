package com.example.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZKRibbonContorller {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ZKRibbonService zkRibbonService;

    @RequestMapping(value = "/ribbonInfo", method = RequestMethod.GET)
    public ServiceInstance getInfo() {
//        this.discoveryClient.getLocalServiceInstance();
        return loadBalancerClient.choose("zk-service");
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public String getStr() {
        return zkRibbonService.getInfo();
    }
}
