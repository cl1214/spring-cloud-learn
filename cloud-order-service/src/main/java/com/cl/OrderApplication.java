package com.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(OrderApplication.class, args);
    }
}
