package com.udea.proyecto.integrador.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class TokenRestTemplate {

    String getUserBalance(String address) {
        String url = "https://token-api-rbf8.onrender.com/tokens/balance/" + address;
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, String.class);

        return response.getBody();
    }

    Boolean updateSellerBalance(String address, String amount) {
        String url = "https://token-api-rbf8.onrender.com/tokens/update_seller_balance/" + address + "?amount=" + amount;

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, null, String.class);

        return mapResponse(response);
    }

    Boolean updateBuyerBalance(String address, String amount) {
        String url = "https://token-api-rbf8.onrender.com/tokens/update_buyer_balance/" + address + "?amount=" + amount;

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, null, String.class);

        return mapResponse(response);
    }

    private boolean mapResponse(ResponseEntity<String> response) {
        return Objects.equals(response.getBody(), "true");
    }

}
