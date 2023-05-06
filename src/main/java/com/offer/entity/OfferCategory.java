package com.offer.entity;

public enum OfferCategory {
    TSS("Tamamlayıcı Sağlık Sigortası"),
    OSS("Özel Sağlık Sigortası"),
    OTHER("Diğer"),
    HS("Hayat Sigortası");

    private final String categoryName;

    OfferCategory(String categoryName){
        this.categoryName=categoryName;
    }

    public String getCategoryName(){
        return this.categoryName;
    }
}


