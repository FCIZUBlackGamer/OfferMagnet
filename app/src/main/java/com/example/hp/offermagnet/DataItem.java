package com.example.hp.offermagnet;

/**
 * Created by hp on 14/04/2018.
 */

public class DataItem {
    public String title ,desc ,dateFrom,dateTo,city;
    public int imageUrl,likes;
    public double price;
    public DataItem(  String title ,String desc ,int imageUrl,String dateFrom,String dateTo,double price,int likes)
    {
        this.title=title;
        this.desc=desc;
        this.imageUrl=imageUrl;
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
        this.price=price;
        this.likes=likes;
    }
    public DataItem(  String title ,String desc ,int imageUrl)
    {
        this.title=title;
        this.desc=desc;
        this.imageUrl=imageUrl;

    }
}
