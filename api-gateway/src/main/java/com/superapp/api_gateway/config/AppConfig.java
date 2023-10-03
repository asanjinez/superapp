package com.superapp.api_gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
        @Bean
        @LoadBalanced
        public WebClient.Builder getWebClientBuilder() {
            return WebClient.builder();
        }

        @Bean
        public WebClient webClientAuth(WebClient.Builder webClientBuilder) {
            return webClientBuilder
                    .clone()
                    .baseUrl("lb://auth-service")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .defaultHeader(HttpHeaders.USER_AGENT, "WebClient")
                    .build();
        }
}
