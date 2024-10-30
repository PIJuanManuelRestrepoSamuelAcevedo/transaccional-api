package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.controller.OfferDTO;

import java.util.List;

public interface TransactionService {

    List<OfferDTO> getOffers();

    String buyOffer(String buyerUsername, String sellerUsername, Long offerId);

    OfferDTO getOfferById(Long offerId);

    List<OfferDTO> getOffersByUsername(String username);
    String saveOffer(OfferDTO offerDTO);
}
