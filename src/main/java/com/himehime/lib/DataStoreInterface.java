package com.himehime.lib;
import java.util.ArrayList;

interface DataStoreInterface {
    ArrayList<Item> readWarehouseItems(String filename);
    CustomerManager  readCustomerList(String path);
    ArrayList<Bill> readBill(String path);
    void loadSettingManager();
    void writeCustomerList(CustomerManager customerManager, String filePath);
    void writeWarehouse(Warehouse warehouse, String filePath);
    void writeBill(ArrayList<Bill> billlist, String filePath);
}
