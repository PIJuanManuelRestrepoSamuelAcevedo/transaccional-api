package com.udea.proyecto.integrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class TokenApiService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String changesTokenOwner(String buyerAddress, String SellerAddress) {
        String url = "http://localhost:8081/url-de-esa-api" ;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .toString();
    }
}
