package com.healthcaremanagement.appointmentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${doctor.url}")
    private String doctorUrl;

    @Bean(name = "doctorWebClient")
    public WebClient webClientDoctor() {

        return WebClient.builder()
                .baseUrl(doctorUrl)
                .build();
    }
}
