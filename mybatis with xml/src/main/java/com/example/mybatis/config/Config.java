package com.example.mybatis.config;

import com.example.mybatis.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Product product(){
        return new Product();
    }
}
