package com.example.hp.offermagnet;

public class DataItemRequest {

    public String title ,desc ,validatedate,city,category;
    public int imageUrl,product;

    public DataItemRequest(String title ,String desc ,int imageUrl,
                           String validatedate,String category,String city,int product)
    {
        this.title=title;
        this.desc=desc;
        this.imageUrl=imageUrl;
        this.validatedate=validatedate;
        this.category=category;
        this.city=city;
        this.product=product;
    }
    public DataItemRequest(  String title ,String desc ,int imageUrl)
    {
        this.title=title;
        this.desc=desc;
        this.imageUrl=imageUrl;

    }
}
