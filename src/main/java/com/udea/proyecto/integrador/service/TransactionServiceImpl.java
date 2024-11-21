package com.udea.proyecto.integrador.service;

import com.udea.proyecto.integrador.Entity.Offer;
import com.udea.proyecto.integrador.config.ApiException;
import com.udea.proyecto.integrador.controller.OfferDTO;
import com.udea.proyecto.integrador.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final OfferRepository offerRepository;
    private final UserRestTemplate userRestTemplate;
    private final TokenRestTemplate tokenRestTemplate;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(OfferRepository offerRepository, UserRestTemplate userRestTemplate, TokenRestTemplate tokenRestTemplate, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRestTemplate = userRestTemplate;
        this.tokenRestTemplate = tokenRestTemplate;
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
            throw new ApiException("offer not found.");
        }
        int offerPrice = offer.get().getPrice();
        int buyerBalance = Integer.parseInt(tokenRestTemplate.getUserBalance(buyerWallet));
        if (buyerBalance < offerPrice) {
            throw new ApiException("User cant buy the offer.");
        }
        offer.get().setUserWallet(buyerWallet);
        offer.get().setOwnerUsername(buyerUsername);

        System.out.println(offerPrice);
        System.out.println(buyerBalance);
        if (!tokenRestTemplate.updateBuyerBalance(buyerWallet, String.valueOf(offerPrice))) {
            System.out.println("buyer");
            System.out.println(String.valueOf(offerPrice));
            throw new ApiException("Fail to buy the offer.");
        }
        if (!tokenRestTemplate.updateSellerBalance(sellerWallet, String.valueOf(offerPrice))) {
            System.out.println("seller");
            throw new ApiException("Fail to buy the offer.");
        }

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
