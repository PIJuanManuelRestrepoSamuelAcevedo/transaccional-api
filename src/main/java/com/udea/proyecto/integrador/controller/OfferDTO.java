package com.udea.proyecto.integrador.controller;

public class OfferDTO {
    private Long offerId;
    private String userAddress;
    private String ownerUsername;
    private float kwhQuantity;
    private float price;
    private float kwhUnitPrice;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getKwhUnitPrice() {
        return kwhUnitPrice;
    }

    public void setKwhUnitPrice(float kwhUnitPrice) {
        this.kwhUnitPrice = kwhUnitPrice;
    }
}