package com.cl.service.impl;

import com.cl.common.dto.PayDto;
import com.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    String url = "http://cloud-payment-service/";


    @Override
    public PayDto getPay(Long id) {

        return restTemplate.getForEntity(url + "pay/getPay?id=" + id, PayDto.class).getBody();
    }
}
