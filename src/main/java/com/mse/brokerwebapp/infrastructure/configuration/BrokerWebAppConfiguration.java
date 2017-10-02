package com.mse.brokerwebapp.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

@Configuration
public class BrokerWebAppConfiguration {

    private static String JSON_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new SimpleDateFormat(JSON_DATETIME_PATTERN));
        return builder;
    }
}
