package com.udea.proyecto.integrador.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    private String userWallet;
    private String ownerUsername;
    private float kwhQuantity;
    private int price;
    private float kwhUnitPrice;
    private String energySource;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(String userWallet) {
        this.userWallet = userWallet;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public float getKwhQuantity() {
        return kwhQuantity;
    }

    public void setKwhQuantity(float kwhQuantity) {
        this.kwhQuantity = kwhQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getKwhUnitPrice() {
        return kwhUnitPrice;
    }

    public void setKwhUnitPrice(float kwhUnitPrice) {
        this.kwhUnitPrice = kwhUnitPrice;
    }

    public String getEnergySource() {
        return energySource;
    }

    public void setEnergySource(String energySource) {
        this.energySource = energySource;
    }
}
