package com.canadafood.order.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
