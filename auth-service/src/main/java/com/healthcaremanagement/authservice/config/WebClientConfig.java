package com.healthcaremanagement.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${patient.url}")
    private String patientUrl;

    @Bean(name = "patientWebClient")
    public WebClient webClient() {

        return WebClient.builder()
            .baseUrl(patientUrl)
            .build();
    }
}
