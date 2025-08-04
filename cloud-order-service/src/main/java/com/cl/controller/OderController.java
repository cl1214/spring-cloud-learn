package com.cl.controller;

import com.cl.common.resp.ResultData;
import com.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("getPay")
    public ResultData getPay(@RequestParam("id") Long id) {
        return ResultData.success(orderService.getPay(id));
    }

    @GetMapping("loadBanlance")
    public String loadBanlance() {
        return orderService.loadBanlance();
    }
}
