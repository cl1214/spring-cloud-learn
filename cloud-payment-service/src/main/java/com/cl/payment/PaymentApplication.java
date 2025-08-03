package com.cl.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.cl.payment.repository")
@EnableDiscoveryClient
public class PaymentApplication
{
    public static void main(String[] args)
    {

        SpringApplication.run(PaymentApplication.class, args);
    }
}
