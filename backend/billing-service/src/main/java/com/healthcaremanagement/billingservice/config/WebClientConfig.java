package com.healthcaremanagement.billingservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${auth.url}")
    private String authUrl;

    @Bean(name = "authWebClient")
    public WebClient webClientAuth() {

        return WebClient.builder()
                .baseUrl(authUrl)
                .build();
    }
}
