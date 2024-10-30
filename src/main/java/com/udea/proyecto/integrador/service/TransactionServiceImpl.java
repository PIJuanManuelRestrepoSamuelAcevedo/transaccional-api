package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.Entity.Offer;
import com.udea.proyecto.integrador.controller.OfferDTO;
import com.udea.proyecto.integrador.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final UserApiService userApiService;
    private final TokenApiService tokenApiService;
    private final OfferRepository offerRepository;
    private final UserRestTemplate userRestTemplate;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(UserApiService userApiService, TokenApiService tokenApiService, OfferRepository offerRepository, UserRestTemplate userRestTemplate, ModelMapper modelMapper) {
        this.userApiService = userApiService;
        this.tokenApiService = tokenApiService;
        this.offerRepository = offerRepository;
        this.userRestTemplate = userRestTemplate;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OfferDTO> getOffers() {
        return offerRepository.findAll().stream().map(this::getOfferDTO).toList();
    }

    @Override
    public String buyOffer(String buyerUsername, String sellerUsername, Long offerId) {
        Long buyerId = Long.valueOf(userRestTemplate.getUsernameFromId(buyerUsername));
        Long sellerId = Long.valueOf(userRestTemplate.getUsernameFromId(sellerUsername));
        String buyerWallet = userRestTemplate.getUserAddress(buyerId.toString());
        String sellerWallet = userRestTemplate.getUserAddress(sellerId.toString());

        Optional<Offer> offer = offerRepository.findByOfferIdAndUserWallet(offerId, sellerWallet);
        if (offer.isEmpty()) {
            return "La oferta no existe.";
        }
        offer.get().setUserWallet(buyerWallet);
        offer.get().setOwnerUsername(buyerUsername);


        offerRepository.save(offer.get());
        return "Cambio exitoso.";
     }

    @Override
    public OfferDTO getOfferById(Long offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        return offer.map(this::getOfferDTO).orElse(null);
    }

    @Override
    public List<OfferDTO> getOffersByUsername(String username) {
        return offerRepository.findAllByOwnerUsername(username).stream().map(this::getOfferDTO).toList();
    }

    @Override
    public String saveOffer(OfferDTO offerDTO) {
        offerRepository.save(getOfferEntity(offerDTO));
        return "Oferta registrada...";
    }

    private OfferDTO getOfferDTO(Offer offer) {
        return modelMapper.map(offer, OfferDTO.class);
    }

    private Offer getOfferEntity(OfferDTO offerDTO) {
        return modelMapper.map(offerDTO, Offer.class);
    }


}
