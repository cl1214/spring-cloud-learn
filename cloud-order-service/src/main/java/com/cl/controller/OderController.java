package com.cl.controller;

import com.alibaba.fastjson2.util.DateUtils;
import com.cl.common.api.PayApi;
import com.cl.common.resp.ResultData;
import com.cl.service.OrderService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
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
    public String circuitBreaker(@RequestParam("id") Long id) {
        if (id == 2) {
            throw new ArithmeticException();
        }
        if (id == 3) {
            throw new NullPointerException();
        }
        if (id == 4) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "正常返回";
//        return payApi.circuitBreaker(id);
    }

    private String fallback(Long id, Throwable ex) {
        return "系统出错";
    }

    @GetMapping("bulkhead")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "bulkFallback", type = Bulkhead.Type.SEMAPHORE)
    public String bulkhead(@RequestParam("id") Long id) {
        return payApi.bulkhead(id);
    }

    private String bulkFallback(Long id, Throwable ex) {
        return "超出最大连接数量";
    }
}
