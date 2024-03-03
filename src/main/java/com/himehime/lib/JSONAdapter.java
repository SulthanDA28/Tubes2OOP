package com.himehime.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.io.IOException;
public class JSONAdapter implements DataStoreInterface{
    public CustomerManager  readCustomerList(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        String jsonstr = readJsonFile(path);
        ArrayList<Customer> customer = objectMapper.readValue(jsonstr, new TypeReference<ArrayList<Customer>>() {});

        CustomerManager customerManager = new CustomerManager();
        for (Customer customer2 : customer) {
            customerManager.addCustomer(customer2);
        }
        return customerManager;
        } catch (Exception e) {
            System.out.println(e);
            return new CustomerManager();
        }
    }
    public ArrayList<Item> readWarehouseItems(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        String jsonstr = readJsonFile(path);
        ArrayList<Item> wareList = objectMapper.readValue(jsonstr, new TypeReference<ArrayList<Item>>() {});
        return wareList;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Item>();
        }
    }
    public ArrayList<Bill> readBill(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        String jsonstr = readJsonFile(path);
        ArrayList<Bill> billlist = objectMapper.readValue(jsonstr, new TypeReference<ArrayList<Bill>>() {});
        Warehouse warehouse = Warehouse.getInstance();
        for (Bill bill : billlist) {
            for (Item item : bill.getItems()) {
                for (Item item2 : warehouse.getInventory()) {
                    if (item.getName().equals(item2.getName())) {
                        item2.addSubscriber(bill);
                    }
                }
            }
        }
        return billlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Bill>();
        }
    }

    public void loadSettingManager(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String jsonstr = readJsonFile("setting.json");
            Map<String, String> map = objectMapper.readValue(jsonstr, new TypeReference<Map<String,String>>() {});
            SettingManager.getInstance().setDataStoreFormat(map.get("format"));
            SettingManager.getInstance().setDataStoreLocation(map.get("location"));
            System.out.println("successfully read settings file.");
        } catch (Exception e) {
            SettingManager.getInstance().setDataStoreFormat("json");
            SettingManager.getInstance().setDataStoreLocation(".\\data\\");
            System.out.println("created new settings file.");
        }
    }

    public void writeSettingManager(){
        try{
            Map<String, String> map = new HashMap<String,String>();
            map.put("format", SettingManager.getInstance().getDataStoreFormat());
            map.put("location", SettingManager.getInstance().getDataStoreLocation());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("setting.json"), map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeCustomerList(CustomerManager customerManager, String filePath) {
        ObjectWriter objectWriter = new ObjectMapper().writerFor(new TypeReference<ArrayList<Customer>>() {}).with(new DefaultPrettyPrinter());;
        try{

            File file = new File(filePath);
            file.getParentFile().mkdirs();
            ArrayList<Customer> customerList = customerManager.getCustomerList();
            objectWriter.writeValue(file,customerList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeWarehouse(Warehouse warehouse, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ArrayList<Item> wareList = warehouse.getInventory();
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,wareList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeBill(ArrayList<Bill> bill, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,bill);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String readJsonFile(String filePath) {
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addItem(new Item().setName("barang1").setPrice(1000).setQuantity(1));
        warehouse.addItem(new Item().setName("barang2").setPrice(2000).setQuantity(2));
        warehouse.addItem(new Item().setName("barang3").setPrice(3000).setQuantity(3));
        warehouse.addItem(new Item().setName("barang4").setPrice(4000).setQuantity(4));
        Bill bill = new Bill();
        bill.addItem(warehouse.getItemByIndex(0),1);
        bill.addItem(warehouse.getItemByIndex(1),1);
        JSONAdapter jsonAdapter = new JSONAdapter();
        ArrayList<Item> wareList = jsonAdapter.readWarehouseItems("JSON.json");
        System.out.println(wareList);
        // jsonAdapter.writeWarehouse(warehouse);
    }

}
