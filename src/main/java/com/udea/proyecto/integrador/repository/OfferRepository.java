package com.udea.proyecto.integrador.repository;

import com.udea.proyecto.integrador.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByUserAddress(String userAddress);
}
