package com.cl.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        // 参数说明：
        // period：重试间隔（ms）
        // maxPeriod：最大重试间隔（ms）
        // maxAttempts：最大重试次数（包含首次调用）
        return new Retryer.Default(
                100,      // 初始重试间隔 100ms
                1000,     // 最大重试间隔 1s
                3         // 最多重试 3 次（首次调用 + 2 次重试）
        );
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
