package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.controller.OfferDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final UserApiService userApiService;
    private final TokenApiService tokenApiService;

    public TransactionServiceImpl(UserApiService userApiService, TokenApiService tokenApiService) {
        this.userApiService = userApiService;
        this.tokenApiService = tokenApiService;
    }


    @Override
    public List<OfferDTO> getOffers() {
        //TODO: obtener las ofertas que hay actualmente, es decir, obtener todos los tokens disponibles.
        //¿Como se mapearian estos tokens?
        return List.of();
    }

    @Override
    public String buyOffer(Long buyerId, Long sellerId) {
        String buyerAddress = userApiService.getUserAddress(buyerId.toString());
        String sellerAddress = userApiService.getUserAddress(sellerId.toString());

        return tokenApiService.changesTokenOwner(buyerAddress, sellerAddress);
     }
}
