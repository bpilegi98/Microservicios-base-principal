package com.ms.principal.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiCallerService {

    private final WebClient webClient;

    public ApiCallerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    public String getWelcomeMessageWithUsernameGet()
    {
        Mono<String> mono = webClient.get()
                .uri("/api1/welcome/")
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }

    public String getWelcomeMessageWithUsernamePost(String username)
    {
        Mono<String> mono = webClient.post()
                .uri("/api1/welcome")
                .body(Mono.just(username), String.class)
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }
}
