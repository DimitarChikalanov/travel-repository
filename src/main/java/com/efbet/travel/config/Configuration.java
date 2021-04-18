package com.efbet.travel.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
