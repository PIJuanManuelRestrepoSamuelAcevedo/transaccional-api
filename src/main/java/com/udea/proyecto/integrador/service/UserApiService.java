package com.udea.proyecto.integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserApiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> getUserAddress(String userId) {
        String url = "http://localhost:8081/users/" + userId + "/address";
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error fetching address");
    }
}
