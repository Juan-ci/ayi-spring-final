package com.example.demo.app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.demo.app.configuration" +
        "com.example.demo.app.app.service" +
        "com.example.demo.app.app.mapper" +
        "com.example.demo.app.app.repository"})
public class CommonsConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
