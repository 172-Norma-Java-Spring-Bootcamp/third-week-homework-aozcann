package com.example.thirdweekhomeworkahmetozcan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// It's create Bean for RestTemplate
@Configuration
public class RestTemplateClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}