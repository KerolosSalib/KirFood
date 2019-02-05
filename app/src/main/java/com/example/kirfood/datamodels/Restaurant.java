package com.example.kirfood.datamodels;

public class Restaurant {



    private String rImage;
    private String rName;
    private String rAddress;
    private float rMinOrder;

    public Restaurant(String  rImage, String rName, String rAddress, float rMinOrder) {
        this.rImage = rImage;
        this.rName = rName;
        this.rAddress = rAddress;
        this.rMinOrder = rMinOrder;
    }


    public String  getrImage() {
        return rImage;
    }



    public String getrName() {
        return rName;
    }



    public String getrAddress() {
        return rAddress;
    }


    public float getrMinOrder() {
        return rMinOrder;
    }







}
