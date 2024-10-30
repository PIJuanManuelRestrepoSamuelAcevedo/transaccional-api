package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.controller.OfferDTO;

import java.util.List;

public interface TransactionService {

    List<OfferDTO> getOffers();

    String buyOffer(Long buyerId, Long sellerId, Long offerId);

    OfferDTO getOfferById(Long offerId);

    List<OfferDTO> getOffersByUsername(String username);
    String saveOffer(OfferDTO offerDTO);
}
