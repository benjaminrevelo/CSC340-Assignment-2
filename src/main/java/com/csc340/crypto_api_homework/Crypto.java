package com.csc340.crypto_api_homework;

public class Crypto {
    public String name;
    public String price;
    public String market_cap;

    public Crypto(String name, String price, String market_cap) {
        this.name = name;
        this.price = price;
        this.market_cap = market_cap;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getMarket_cap(){
        return market_cap;
    }

    public void setMarket_cap(String market_cap){
        this.market_cap = market_cap;
    }
}