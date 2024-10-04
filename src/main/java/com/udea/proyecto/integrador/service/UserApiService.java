package com.udea.proyecto.integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserApiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String getUserAddress(String userId) {
        String url = "http://localhost:8080/" + userId + "/address";
        return webClientBuilder.build()
                .get()
                .uri(url)
                .toString();
    }
}
