package com.himehime.lib;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bill implements ItemSubscriber {
    private ArrayList<Item> items;
    private int customerID;
    private boolean isCompleted;

    // default ctor, user-defined ctor, cctor
    public Bill() {
        this.items = new ArrayList<Item>();
        this.customerID = 0;
        this.isCompleted = false;
    }
    public Bill(int id, String date, ArrayList<Item> items, int customerID, boolean isCompleted) {
        this.items = items;
        this.customerID = customerID;
        this.isCompleted = isCompleted;
    }
    public Bill(Bill other) {
        this.items = new ArrayList<Item>(other.items);
        this.customerID = other.customerID;
        this.isCompleted = other.isCompleted;
    }

    // setter
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    // getter
    public ArrayList<Item> getItems() {
        return this.items;
    }
    public int getCustomerID() {
        return this.customerID;
    }
    public boolean getCompleted() {
        return this.isCompleted;
    }

    // other methods
    public Item getItemByIdx(int idx) {
        return this.items.get(idx);
    }

    @JsonIgnore
    public int getSubTotalPrice() {
        int total = 0;
        for (Item item : this.items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public void addItem(Item item, int quantity) {
        Item itemBill = new Item(item).setQuantity(quantity);
        this.items.add(itemBill);
        item.addSubscriber(this);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.removeSubscriber(this);
    }

    @Override
    public void update(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == item.getId()) {
                Item itemBill = new Item(item).setQuantity(this.items.get(i).getQuantity());
                this.items.set(i, itemBill);
                break;
            }
        }
    }

    // test
    public static void main(String[] args) {
        Bill bill = new Bill();
        System.out.println(bill.getSubTotalPrice());
    }
}
