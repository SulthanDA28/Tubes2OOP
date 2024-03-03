package com.himehime.lib;
import java.util.Arrays;

public class MementoItem {
    private String name;
    private int quantity;
    private int price;
    private int buyPrice;
    private String category;
    private byte[] picture;

    //ctor
    public MementoItem(){
        this.name = "";
        this.quantity = 0;
        this.price = 0;
        this.buyPrice = 0;
        this.category = "";
        this.picture = new byte[0];
    }

    public MementoItem(Item x){
        this.name = x.getName();
        this.quantity = x.getQuantity();
        this.price = x.getPrice();
        this.buyPrice = x.getBuyPrice();
        this.category = x.getCategory();
        this.picture = Arrays.copyOf(x.getPicture(), x.getPicture().length);
    }
    //getter
    public Item getItemState()
    {
        return new Item()
            .setName(this.name)
            .setQuantity(this.quantity)
            .setPrice(this.price)
            .setBuyPrice(this.buyPrice)
            .setCategory(this.category)
            .setPicture(this.picture);
    }

    public void setItemState(Item x)
    {
        this.name = x.getName();
        this.quantity = x.getQuantity();
        this.price = x.getPrice();
        this.buyPrice = x.getBuyPrice();
        this.category = x.getCategory();
        this.picture = Arrays.copyOf(x.getPicture(), x.getPicture().length);
    }

}
