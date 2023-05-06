package com.offer.advice;

public class OfferNotFound extends RuntimeException {
    public OfferNotFound(String message){
        super(message);
    }
}
