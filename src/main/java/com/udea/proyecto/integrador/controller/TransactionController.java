package com.udea.proyecto.integrador.controller;

import com.udea.proyecto.integrador.Entity.Offer;
import com.udea.proyecto.integrador.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/get-offers")
    ResponseEntity<List<OfferDTO>> getOffers() {
        return ResponseEntity.ok(transactionServiceImpl.getOffers());
    }

    @PostMapping("/save-offer")
    ResponseEntity<Offer> saveOffer(@RequestBody Offer offer) {
        //TODO: como se creará la oferta?
        return ResponseEntity.ok(new Offer());
    }

    @PutMapping("/buy-offer")
    ResponseEntity<String> buyOffer(@RequestParam Long buyerId, @RequestParam Long sellerId){
        return ResponseEntity.ok(transactionServiceImpl.buyOffer(buyerId, sellerId));
    }

}
