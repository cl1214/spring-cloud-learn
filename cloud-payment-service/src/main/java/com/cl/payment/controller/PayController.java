package com.cl.payment.controller;

import com.cl.payment.entity.Pay;
import com.cl.payment.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping("getPay")
    public Pay getPay(@RequestParam("id") Long id) {
        return payService.getPay(id);
    }
}
