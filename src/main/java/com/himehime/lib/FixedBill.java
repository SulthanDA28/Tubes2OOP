package com.himehime.lib;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FixedBill {
    private String date;

    private ArrayList<MementoItem> items;
    @JsonProperty("customerID")
    private int customerID;
    @JsonProperty("discount")
    private int discount;
    @JsonProperty("subTotal")
    private int subTotal;
    
    // ctor
    public FixedBill() {
        this.date = "";
        this.items = new ArrayList<MementoItem>();
        this.customerID = 0;
        this.discount = 0;
        this.subTotal = 0;
    }

    public FixedBill(Bill other, int customerID, String date, int discount) {
        this.date = date;
        this.items = new ArrayList<MementoItem>();
        for (int i = 0; i < other.getItems().size(); i++) {
            this.items.add(new MementoItem(other.getItems().get(i)));
        }
        this.customerID = customerID;
        this.discount = discount;
        this.subTotal = other.getSubTotalPrice();
    }

    //setter
    public void setDate(String date) {
        this.date = date;
    }
    public void setItems(ArrayList<MementoItem> items) {
        this.items = items;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    // getter
    public String getDate() {
        return this.date;
    }
    public ArrayList<MementoItem> getItems() {
        return this.items;
    }
    public int getCustomerID() {
        return this.customerID;
    }

    public int getDiscount() {
        return this.discount;
    }

    public int getSubTotal() {
        return this.subTotal;
    }

    // other methods
    public Item getItemByIdx(int idx) {
        Item item = this.items.get(idx).getItemState();
        return item;
    }

    @JsonIgnore
    public int getSubTotalPrice() {
        int total = 0;
        for (MementoItem mementoItem : this.items) {
            Item item = mementoItem.getItemState();
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // test
    public static void main(String[] args) {
        Bill bill = new Bill();
        FixedBill fixedBill = new FixedBill(bill, 1, "2020-01-01", 10);
        System.out.println(fixedBill.getSubTotalPrice());
    }
}
