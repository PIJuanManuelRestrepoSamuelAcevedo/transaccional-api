package com.udea.proyecto.integrador.repository;

import com.udea.proyecto.integrador.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByUserAddress(String userAddress);
    List<Offer> findAllByOwnerUsername(String username);
    Optional<Offer> findByOfferIdAndUserAddress(Long offerId, String userAddress);
}
