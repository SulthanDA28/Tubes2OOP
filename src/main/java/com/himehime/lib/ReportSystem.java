package com.himehime.lib;

import java.util.ArrayList;

public class ReportSystem {

    public static void printSalesReport(ArrayList<Customer> customerList){
        System.out.println("Sales Report");
        System.out.println("============");
        Runnable salesSleeper = new Runnable() {
            @Override
            public void run() {
                try {
                    for (Customer customer : customerList) {
                        System.out.println(customer.getTransactionList());
                    };
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread salesThread = new Thread(salesSleeper);
        salesThread.setDaemon(true);
        salesThread.start();
        
    }
    public static void printFixedBill(FixedBill fixedBill){
        System.out.println("Fixed Bill");
        System.out.println("==========");
        Runnable fixedBillSleeper = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Date: " + fixedBill.getDate());
                    System.out.println("Customer ID: " + fixedBill.getCustomerID() + "\n");
                    String detail = String.format("%-15s %-10s %-10s %s", "Name", "Quantity", "Price", "Total Price");
                    System.out.println("    " + detail);
                    for (MementoItem mementoItem : fixedBill.getItems()) {
                        Item item = mementoItem.getItemState();
                        String itemDetail = String.format("%-15s %-10s %-10d %d", item.getName(), String.format(String.valueOf(item.getQuantity())), item.getPrice(), item.getPrice()*item.getQuantity());
                        System.out.println("    " + itemDetail);
                    }
                    System.out.println("\nDiscount: " + fixedBill.getDiscount());
                    System.out.println("Subtotal: " + fixedBill.getSubTotal());
                    System.out.println("Total: " + (fixedBill.getSubTotal() - fixedBill.getDiscount()));
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread fixedBillThread = new Thread(fixedBillSleeper);
        fixedBillThread.setDaemon(true);
        fixedBillThread.start();
    }
}
