package com.codean.CoffeShop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeeShopConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
