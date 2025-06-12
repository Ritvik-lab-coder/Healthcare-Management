package com.healthcaremanagement.doctorservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Value("${patient.url}")
    private String patientServiceUrl;

    @Bean(name = "patientWebClient")
    public WebClient webClient() {

        return WebClient.builder()
                .baseUrl(patientServiceUrl)
                .build();
    }
}
