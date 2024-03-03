package com.himehime.lib;

import java.util.ArrayList;

public class Dummy {
    public static ArrayList<Customer> createCustomers() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        Customer customer1 = new Customer(1);
        customerList.add(customer1);
        Customer customer2 = new Member(2, "Himehime", "+62-812-3456-7890");
        customerList.add(customer2);
        Customer customer3 = new VIPMember(3, "Daisuki", "+62-812-3456-7890");
        customerList.add(customer3);
        return customerList;
    }

    public static ArrayList<Item> createItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        Item item1 = new Item().setId(1).setName("Kopi").setPrice(10000).setQuantity(10);
        itemList.add(item1);
        Item item2 = new Item().setId(2).setName("Teh").setPrice(5000).setQuantity(10);
        itemList.add(item2);
        Item item3 = new Item().setId(3).setName("Susu").setPrice(15000).setQuantity(10);
        itemList.add(item3);
        return itemList;
    }

    public static ArrayList<Bill> createBills() {
        ArrayList<Item> itemList = createItems();
        ArrayList<Bill> billList = new ArrayList<Bill>();
        Bill bill1 = new Bill(1, "2020-01-01", itemList, 1, true);
        billList.add(bill1);
        Bill bill2 = new Bill(2, "2020-01-02", itemList, 2, true);
        billList.add(bill2);
        Bill bill3 = new Bill(3, "2020-01-03", itemList, 3, true);
        billList.add(bill3);
        return billList;
    }

    public static ArrayList<FixedBill> createTransactionList() {
        ArrayList<Bill> billList = createBills();
        ArrayList<FixedBill> transactionList = new ArrayList<FixedBill>();
        FixedBill transaction1 = new FixedBill(billList.get(0), 1, "2020-01-01", 10);
        transactionList.add(transaction1);
        FixedBill transaction2 = new FixedBill(billList.get(1), 2, "2020-01-02", 10);
        transactionList.add(transaction2);
        FixedBill transaction3 = new FixedBill(billList.get(2), 3, "2020-01-03", 10);
        transactionList.add(transaction3);
        return transactionList;
    }
}
