package com.cl.common.api;

import com.cl.common.dto.PayDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cloud-payment-service")
public interface PayApi {

    @GetMapping("pay/getPay")
    public PayDto getPay(@RequestParam("id") Long id);

    @GetMapping("pay/loadBanlance")
    public String loadBanlance();

    @GetMapping("pay/timeOut")
    public void timeOut();

}
