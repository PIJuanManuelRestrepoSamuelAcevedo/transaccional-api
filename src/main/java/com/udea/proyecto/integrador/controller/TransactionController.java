package com.udea.proyecto.integrador.controller;

import com.udea.proyecto.integrador.repository.OfferRepository;
import com.udea.proyecto.integrador.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/get-offers")
    ResponseEntity<List<OfferDTO>> getOffers() {
        return ResponseEntity.ok(transactionService.getOffers());
    }

    @GetMapping("/get-offer/{offerId}")
    ResponseEntity<OfferDTO> getOfferFromId(@PathVariable Long offerId) {
        return ResponseEntity.ok(transactionService.getOfferById(offerId));
    }

    @GetMapping("/get-offers/{username}")
    ResponseEntity<List<OfferDTO>> getOffersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(transactionService.getOffersByUsername(username));
    }

    @PostMapping("/save-offer")
    ResponseEntity<String> saveOffer(@RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(transactionService.saveOffer(offerDTO));
    }

    @PutMapping("/buy-offer")
    ResponseEntity<String> buyOffer(@RequestParam String buyerUsername, @RequestParam String sellerUsername, @RequestParam Long offerId){
        return ResponseEntity.ok(transactionService.buyOffer(buyerUsername, sellerUsername, offerId));
    }

}
