package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.Entity.Offer;
import com.udea.proyecto.integrador.controller.OfferDTO;
import com.udea.proyecto.integrador.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public String buyOffer(Long buyerId, Long sellerId, Long offerId) {
        String buyerAddress = userRestTemplate.getUserAddress(buyerId.toString());
        String sellerAddress = userRestTemplate.getUserAddress(sellerId.toString());

        Optional<Offer> offer = offerRepository.findByOfferIdAndUserAddress(offerId, sellerAddress);
        if (offer.isEmpty()) {
            return "La oferta no existe.";
        }
        offer.get().setUserAddress(buyerAddress);

        offerRepository.save(offer.get());
        return "Cambio exitoso.";
     }

    @Override
    public OfferDTO getOfferById(Long offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        return offer.map(this::getOfferDTO).orElse(null);
    }

    @Override
    public List<OfferDTO> getOffersByUserAddress(String username) {
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
