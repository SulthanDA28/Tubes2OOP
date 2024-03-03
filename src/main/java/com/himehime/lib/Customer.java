package com.himehime.lib;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type"
)
@JsonSubTypes({
    @Type(value = Member.class, name = "Member"),
    @Type(value = VIPMember.class, name = "VIPMember")
})

public class Customer {
    protected int id; 
    protected ArrayList<FixedBill> transactionList;

    // ctor
    public Customer() {
        this.id = 0;
        this.transactionList = new ArrayList<FixedBill>();
    }

    public Customer(int id) {
        this.id = id;
        this.transactionList = new ArrayList<FixedBill>();
    }
    // cctor
    public Customer(Customer customer) {
        this.id = customer.id;
        this.transactionList = customer.transactionList;
    }
    // Getter methods
    public int getId() {
        return id;
    }

    public ArrayList<FixedBill> getTransactionList() {
        return this.transactionList;
    }
    
    // Add and remove transaction methods
    public void addTransaction(FixedBill transaction) {
        if(this.transactionList.size() < 1){
            this.transactionList.add(transaction);
        }
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setTransactionList(ArrayList<FixedBill> transactionList) {
        this.transactionList = transactionList;
    }
    

    // Upgrade methods
    public Member upgradeToMember(String name, String phone) {
        Member member = new Member(this.id, name, phone);
        return member;
    }
    
}
