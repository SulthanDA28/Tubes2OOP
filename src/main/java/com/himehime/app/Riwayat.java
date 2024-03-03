package com.himehime.app;

import com.himehime.lib.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import javafx.collections.FXCollections;

public class Riwayat extends Tab {
    public Riwayat(){
        super("Riwayat");
        CustomerManager customerManager = new CustomerManager();
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        Label labelRiwayat = new Label("Riwayat Transaksi\nCustomer");
        labelRiwayat.getStyleClass().add("title");

        ComboBox<String> idComboBox = new ComboBox<String>();
        idComboBox.getStyleClass().add("combo-box");
        idComboBox.setPromptText("Select a valid ID");

        ArrayList<String> idList = new ArrayList<String>();
        for (int i = 0; i < customerManager.getCustomerList().size(); i++) {
            if(customerManager.getCustomerList().get(i) instanceof Member 
            || customerManager.getCustomerList().get(i) instanceof VIPMember){
                idList.add(customerManager.getCustomerList().get(i).getId() + " - " + ((Member) customerManager.getCustomerList().get(i)).getName());
            }else{
                idList.add(String.valueOf(customerManager.getCustomerList().get(i).getId()));
            }
        }
        idComboBox.setItems(FXCollections.observableArrayList(idList));

        // create a button
        Button button = new Button("Tampilkan Riwayat");
        button.getStyleClass().add("button-kecil");

        // create riwayat
        VBox transactionBox = new VBox();

        // create a scrollpane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(transactionBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefSize(540, 680);

        // add label
        root.getChildren().add(labelRiwayat);
        root.getChildren().add(idComboBox);
        root.getChildren().add(button);
        root.getChildren().add(scrollPane);

        // set x and y for label
        labelRiwayat.setLayoutX(75);
        labelRiwayat.setLayoutY(180);
        idComboBox.setLayoutX(180);
        idComboBox.setLayoutY(330);
        button.setLayoutX(180);
        button.setLayoutY(380);
        scrollPane.setLayoutX(560);
        scrollPane.setLayoutY(40);
  
        setContent(root);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int customerID = Integer.parseInt(idComboBox.getValue().split(" - ")[0]);
                    Customer customer = customerManager.getCustomerByID(customerID);
                    ArrayList<FixedBill> transactionList = customer.getTransactionList();
                    transactionBox.getChildren().clear();
                    VBox transactionBox = createRiwayat(transactionList);
                    scrollPane.setContent(transactionBox);
                } catch (NumberFormatException e) {
                    return;
                }
            }
        });
    }

    public VBox createRiwayat(ArrayList<FixedBill> transactionList) {
        VBox transactionBox = new VBox();

        for (int i = 0; i < transactionList.size(); i++) {
            Label labelDate = new Label("\t\t\t\t\t\t\t\t" + transactionList.get(i).getDate());
            labelDate.getStyleClass().add("sub-title");
            Label labelItem = new Label("\t\t\t\tItem:");
            labelItem.getStyleClass().add("sub-title");
            transactionBox.getChildren().add(labelDate);
            transactionBox.getChildren().add(labelItem);
            ArrayList<MementoItem> itemList = transactionList.get(i).getItems();
            for (int j = 0; j < itemList.size(); j++) {
                Item item = itemList.get(j).getItemState();
                String itemDetail = String.format("%-15s %-10s %d", item.getName(), String.format(String.valueOf(item.getQuantity())+"x"), item.getPrice());
                Label labelItemDetail = new Label("\t\t\t\t\t\t" + itemDetail);
                labelItemDetail.getStyleClass().add("sub-title");
                transactionBox.getChildren().add(labelItemDetail);
            }
            Label labelTotal = new Label("\t\t\t\tSubtotal: \t" + transactionList.get(i).getSubTotalPrice());
            labelTotal.getStyleClass().add("sub-title");
            Label labelNewLine = new Label("\n");
            transactionBox.getChildren().add(labelTotal);
            transactionBox.getChildren().add(labelNewLine);
        }

        return transactionBox;
    }
}