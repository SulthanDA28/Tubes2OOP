package com.himehime.lib;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class Item {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private int buyPrice;
    private int sellPrice;
    private String category;
    private byte[] picture;
    private boolean removed;
    @JsonIgnore
    private ArrayList<ItemSubscriber> subscribers;
    //ctor
    public Item() {
        this.id = 0;
        this.name = "";
        this.quantity = 0;
        this.price = 0;
        this.buyPrice = 0;
        this.sellPrice = 0;
        this.category = "";
        this.picture = new byte[0];
        this.removed = false;
        this.subscribers = new ArrayList<ItemSubscriber>();
    }
    //ctor
    public Item(Item x) {
        this.id = x.id;
        this.name = x.name;
        this.quantity = x.quantity;
        this.price = x.price;
        this.buyPrice = x.buyPrice;
        this.sellPrice = x.sellPrice;
        this.category = x.category;
        this.picture = Arrays.copyOf(x.picture, x.picture.length);
        this.removed = x.removed;
        this.subscribers = new ArrayList<ItemSubscriber>();
    }
    //setter
    public Item setId(int id) {
        this.id = id;
        return this;
    }
    public Item setName(String name) {
        this.name = name;
        return this;
    }
    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
    public Item setPrice(int price) {
        this.price = price;
        return this;
    }
    public Item setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
        return this;
    }
    public Item setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
        return this;
    }
    public Item setCategory(String category) {
        this.category = category;
        return this;
    }
    public Item setPicture(byte[] picture) {
        this.picture = Arrays.copyOf(picture, picture.length);
        return this;
    }
    public Item setRemoved(boolean removed) {
        this.removed = removed;
        return this;
    }
    public Item setSubscribers(ArrayList<ItemSubscriber> subscribers) {
        this.subscribers = new ArrayList<ItemSubscriber>();
        for(ItemSubscriber i : subscribers)
        {
            this.subscribers.add(i);
        }
        return this;
    }
    //getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public int getBuyPrice() {
        return buyPrice;
    }
    public int getSellPrice() {
        return sellPrice;
    }
    public String getCategory() {
        return category;
    }
    public byte[] getPicture() {
        return picture;
    }
    public boolean getRemoved() {
        return removed;
    }
    public ArrayList<ItemSubscriber> getSubscribers() {
        return subscribers;
    }
    //method
    public void delete()
    {
        removed = true;
    }
    public boolean isRemoved()
    {
        return removed;
    }
    public void addSubscriber(ItemSubscriber x) {
        subscribers.add(x);
    }
    public void removeSubscriber(ItemSubscriber x) {
        java.util.Iterator<ItemSubscriber> itr = this.subscribers.iterator();
        while (itr.hasNext()) {
            ItemSubscriber i = itr.next();
            if (i.equals(x)) {
                itr.remove();
            }
        }
    }
    public void modify(Item x){
        this.id = x.id;
        this.name = x.name;
        this.quantity = x.quantity;
        this.price = x.price;
        this.buyPrice = x.buyPrice;
        this.sellPrice = x.sellPrice;
        this.category = x.category;
        this.picture = x.picture;
        this.removed = x.removed;
        this.subscribers = new ArrayList<ItemSubscriber>(x.subscribers);
    }
    
}
