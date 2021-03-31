package com.ms.principal.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientConfig {

    WebClient webClient = WebClient.builder()
            .baseUrl("localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();


    public Mono<String> get()
    {
        return webClient.get()
                .uri("/api/welcome")
                .retrieve()
                .bodyToMono(String.class);

    }

    public Mono<String> post(String username)
    {
        return webClient.post()
                .uri("/api/welcome")
                .body(Mono.just(username), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }


}
