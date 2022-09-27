package com.spring.ayi.app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.spring.ayi.app.configuration" +
        "com.spring.ayi.app.service" +
        "com.spring.ayi.app.mapper" +
        "com.spring.ayi.app.repository"})
public class CommonsConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
