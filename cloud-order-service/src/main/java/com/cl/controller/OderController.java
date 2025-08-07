package com.cl.controller;

import com.alibaba.fastjson2.util.DateUtils;
import com.cl.common.api.PayApi;
import com.cl.common.resp.ResultData;
import com.cl.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order")
public class OderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PayApi payApi;

    @GetMapping("getPay")
    public ResultData getPay(@RequestParam("id") Long id) {
        return ResultData.success(orderService.getPay(id));
    }

    @GetMapping("loadBanlance")
    public String loadBanlance() {
        return orderService.loadBanlance();
    }

    @GetMapping("discovery")
    public void discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(e -> System.out.println(e));
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        instances.forEach(e -> System.out.println(e));

    }

    @GetMapping("getPayFeign")
    public ResultData getPayFeign(@RequestParam("id") Long id) {
        return ResultData.success(payApi.getPay(id));
    }

    @GetMapping("loadBanlanceFeign")
    public String getPayFeign() {
        return payApi.loadBanlance();
    }

    @GetMapping("timeOut")
    public void timeOut() {
        System.out.println(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        payApi.timeOut();
        System.out.println(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }


    @GetMapping("circuitBreaker")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "fallback")
    public String circuitBreaker(Long id) {
        return payApi.circuitBreaker(id);
    }

    private String fallback(Long id, Throwable ex) {
        return "系统出错";
    }
}
