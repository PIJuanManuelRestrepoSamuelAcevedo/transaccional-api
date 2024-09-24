package com.udea.proyecto.integrador.controller;

import com.udea.proyecto.integrador.Entity.Offer;
import com.udea.proyecto.integrador.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/get-offers")
    ResponseEntity<List<Offer>> getOffers() {
        //TODO: Obtener ofertas, de donde??
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/save-offer")
    ResponseEntity<Offer> saveOffer(@RequestBody Offer offer) {
        //TODO: como se creará la oferta?
        return ResponseEntity.ok(new Offer());
    }

    @PutMapping("/buy-offer")
    ResponseEntity<Offer> buyOffer(){
        //TODO: Cambiar la billetera de un token, se recibe el usuario comprador y vendedor para obtener la billetera
        // y hacer el cambio.
        return ResponseEntity.ok(new Offer());
    }

}
