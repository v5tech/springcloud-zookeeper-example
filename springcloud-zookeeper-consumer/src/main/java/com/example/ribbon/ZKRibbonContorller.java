package com.example.ribbon;

import com.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZKRibbonContorller {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ZKRibbonService zkRibbonService;

    @RequestMapping(value = "/ribbon", method = RequestMethod.GET)
    public ServiceInstance ribbon() {
//        this.discoveryClient.getLocalServiceInstance();
        return loadBalancerClient.choose("zk-service");
    }

    @RequestMapping(value = "/ribbon/welcome", method = RequestMethod.GET)
    public String welcome() {
        return zkRibbonService.welcome();
    }

    @RequestMapping(value = "/ribbon/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Integer id) {
        return zkRibbonService.findById(id);
    }

    @RequestMapping(value = "/ribbon/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return zkRibbonService.findAll();
    }

}
