# springcloud-zookeeper-example

使用Feign、Ribbon客户端负载均衡和Hystrix熔断机制的ZooKeeper为注册中心的微服务示例

* springcloud-zookeeper-service

提供服务，随机端口，可启动多个

* springcloud-zookeeper-consumer

使用`Feign`、`Ribbon`负载均衡消费`springcloud-zookeeper-service`提供的服务。

* springcloud-zookeeper-hystrixdashboard

Hystrix Dashboard

# Hystrix

http://localhost:8080/hystrix.stream

# Hystrix Dashboard

http://localhost:9080/hystrix