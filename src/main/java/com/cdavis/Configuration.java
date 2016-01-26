package com.cdavis;

import com.cdavis.errorhandling.ErrorHandler;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ErrorHandler errorHandler(){
        return new ErrorHandler();
    }
}
