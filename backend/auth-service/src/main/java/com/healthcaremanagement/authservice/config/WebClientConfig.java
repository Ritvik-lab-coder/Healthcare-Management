package com.healthcaremanagement.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${patient.url}")
    private String patientUrl;

    @Value("${doctor.url}")
    private String doctorUrl;

    @Bean(name = "patientWebClient")
    public WebClient webClientPatient() {

        return WebClient.builder()
            .baseUrl(patientUrl)
            .build();
    }

    @Bean(name = "doctorWebClient")
    public WebClient webClientDoctor() {

        return WebClient.builder()
            .baseUrl(doctorUrl)
            .build();
    }
}
