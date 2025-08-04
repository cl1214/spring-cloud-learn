package com.cl.payment.controller;

import com.cl.payment.entity.Pay;
import com.cl.payment.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @Value("${consule.testvalue}")
    private String confValue;

    @GetMapping("getPay")
    public Pay getPay(@RequestParam("id") Long id) {
        return payService.getPay(id);
    }

    @GetMapping("getConfig")
    public String getConfig() {
        return confValue;
    }

    @GetMapping("loadBanlance")
    public String loadBanlance() {
        return "cloud-payment-service:" + port;
    }
}
