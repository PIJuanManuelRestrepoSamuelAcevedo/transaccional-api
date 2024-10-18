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
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(UserApiService userApiService, TokenApiService tokenApiService, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.userApiService = userApiService;
        this.tokenApiService = tokenApiService;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OfferDTO> getOffers() {
        return offerRepository.findAll().stream().map(this::getOfferDTO).toList();
    }

    @Override
    public String buyOffer(Long buyerId, Long sellerId) {
        String buyerAddress = userApiService.getUserAddress(buyerId.toString());
        String sellerAddress = userApiService.getUserAddress(sellerId.toString());

        return tokenApiService.changesTokenOwner(buyerAddress, sellerAddress);
     }

    @Override
    public OfferDTO getOfferById(Long offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        return offer.map(this::getOfferDTO).orElse(null);
    }

    @Override
    public List<OfferDTO> getOffersByUserAddress(String userAddress) {
        return offerRepository.findAllByUserAddress(userAddress).stream().map(this::getOfferDTO).toList();
    }

    private OfferDTO getOfferDTO(Offer offer) {
        return modelMapper.map(offer, OfferDTO.class);
     }
}
