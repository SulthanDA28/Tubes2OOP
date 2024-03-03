package com.himehime.lib;
import java.util.ArrayList;
public class Warehouse {
    private ArrayList<Item> inventory;
    private static Warehouse instance;
    
    //ctor
    private Warehouse() {
        this.inventory = new ArrayList<Item>();
    }
    //setter
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
    //getter
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    public static Warehouse getInstance() {
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }
    //method
    public void addItem(Item x) {
        this.inventory.add(x);
    }
    public void removeItemByIndex(int x) {
        this.inventory.remove(x);
    }
    public Item getItemByIndex(int x) {
        return this.inventory.get(x);
    }
    public Item getItemByName(String x) {
        for(Item item : this.inventory) {
            if(item.getName().equals(x)) {
                return item;
            }
        }
        return null;
    }
    public ArrayList<String> getAllCategory() {
        ArrayList<String> categoryList = new ArrayList<String>();
        for(Item item : this.inventory) {
            if(!categoryList.contains(item.getCategory())) {
                categoryList.add(item.getCategory());
            }
        }
        return categoryList;
    }
}
