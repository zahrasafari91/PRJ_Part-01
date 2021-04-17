package com.zahra.ecommerceapp.models;

public class EventModel {

    private String  name, imgUrl, brand, description;
    private String price;
    private int id , count;

    public EventModel(String name, String imgUrl, String brand, String priceSign, String currency, String price, int id, String description) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.brand = brand;
        this.price = priceSign +" " + currency + " " + price ;
        this.id = id;
        this.count = 0;
        this.description = description;
    }

    public void incrementCount() {
        this.count+=1 ;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getBrand() {
        return brand;
    }


    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
