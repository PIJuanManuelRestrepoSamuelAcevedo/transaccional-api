package com.udea.proyecto.integrador.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserRestTemplate {

    String getUserAddress(String userId) {
        String url = "https://user-api-g4ut.onrender.com/users/" + userId + "/wallet";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, String.class);

        return response.getBody();
    }

    String getUsernameFromId(String username) {
        String url = "https://user-api-g4ut.onrender.com/users/" + username + "/user_id";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, String.class);

        return response.getBody();
    }
}
