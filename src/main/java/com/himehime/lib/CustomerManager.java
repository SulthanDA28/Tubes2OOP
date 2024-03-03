package com.himehime.lib;

import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customerList;
    
    public CustomerManager() {
        this.customerList = new ArrayList<Customer>();
    }
    
    public void addCustomer(Customer customer) {
        int id = this.customerList.size();
        customer.setId(id);
        this.customerList.add(customer);
    }
    
    public Customer getCustomerByID(int id) {
        for (Customer customer : this.customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    public void setCustomerByID(int id, Customer newCustomer){
        for(int i = 0; i < this.customerList.size(); i++){
            if(this.customerList.get(i).getId() == id){
                this.customerList.set(i, newCustomer);
            }
        }
    }
    
    public Customer getCustomerByIdx(int index) {
        if (index < 0 || index >= this.customerList.size()) {
            return null;
        }
        return this.customerList.get(index);
    }
    public ArrayList<FixedBill> getAllTransaction() {
        ArrayList<FixedBill> transactionList = new ArrayList<FixedBill>();
        for (Customer customer : this.customerList) {
            transactionList.addAll(customer.getTransactionList());
        }
        return transactionList;
    }
    
    public ArrayList<Customer> getCustomerList() {
        return this.customerList;
    }
    public ArrayList<String> getCustomerIDList() {
        ArrayList<String> customerIDList = new ArrayList<String>();
        for (Customer customer : this.customerList) {
            String str = String.valueOf(customer.getId());
            customerIDList.add(str);
        }
        return customerIDList;
    }
}

