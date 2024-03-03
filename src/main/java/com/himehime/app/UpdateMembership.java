package com.himehime.app;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.Integer;
import java.util.ArrayList;

import com.himehime.lib.*;
import javafx.collections.FXCollections;

public class UpdateMembership extends Tab{
    public UpdateMembership(CustomerManager customerManager){
        super("Update Membership");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Create image
        Image update_img = new Image(getClass().getResource("/updateImage.png").toExternalForm());
        ImageView update_image = new ImageView(update_img);
        update_image.setX(250);
        update_image.setY(120);
        update_image.setFitHeight(720);
        update_image.setFitWidth(1080);

        // Create image
        Image hor_img = new Image(getClass().getResource("/hor.png").toExternalForm());
        ImageView hor_image = new ImageView(hor_img);
        hor_image.setX(0);
        hor_image.setY(650);
        hor_image.setFitHeight(293);
        hor_image.setFitWidth(1280);

        // Create a title text
        Text text = new Text();
        text.setText("Update Membership for\nHimehime's Store");
        text.getStyleClass().add("title");
        text.setX(75);
        text.setY(180);

        // Create a ComboBox
        Text member = new Text();
        member.setText("Member");
        member.setX(75);
        member.setY(280);
        member.getStyleClass().add("sub-title");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("combo-box");
        comboBox.getItems().addAll("Member", "VIP Member");
        comboBox.setLayoutX(60);
        comboBox.setLayoutY(290);
        comboBox.setPromptText("Select an option");

        // Create a ComboBox
        Text status = new Text();
        status.setText("Status");
        status.setX(315);
        status.setY(280);
        status.getStyleClass().add("sub-title");
        ComboBox<String> comboBoxA = new ComboBox<>();
        comboBoxA.getStyleClass().add("combo-box");
        comboBoxA.getItems().addAll("Active", "Inactive");
        comboBoxA.setLayoutX(300);
        comboBoxA.setLayoutY(290);
        comboBoxA.setPromptText("Select an option");


        // Create a TextField for name
        Text idText = new Text();
        idText.setText("ID");
        idText.setX(75);
        idText.setY(370);
        idText.getStyleClass().add("sub-title");
        
        ComboBox<String> idComboBox = new ComboBox<String>();
        idComboBox.getStyleClass().add("combo-box");
        idComboBox.setLayoutX(60);
        idComboBox.setLayoutY(380);
        idComboBox.setPromptText("Select a valid ID");
        // idSelection.getItems().addAll(customerManager.getCustomerList());
        customerManager.addCustomer(new Customer(1));
        customerManager.addCustomer(new Member(2, "Himehime", "+62-812-3456-7890"));
        System.out.println(customerManager.getCustomerIDList());
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

        // Create a TextField for name
        Text nama = new Text();
        nama.setText("Nama");
        nama.setX(75);
        nama.setY(450);
        nama.getStyleClass().add("sub-title");
        TextField nameField = new TextField();
        nameField.getStyleClass().add("input-field");
        nameField.setLayoutX(60);
        nameField.setLayoutY(460);
        nameField.setPromptText("Enter your name");
        String name = nameField.getText();

        // Create a TextField for phone number
        Text telp = new Text();
        telp.setText("Nomor Telepon");
        telp.setX(75);
        telp.setY(530);
        telp.getStyleClass().add("sub-title");
        TextField phoneField = new TextField();
        phoneField.getStyleClass().add("input-field");
        phoneField.setLayoutX(60);
        phoneField.setLayoutY(540);
        phoneField.setPromptText("+62-XXXX-XXXX-XXX");
        String phone = phoneField.getText();

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button-kecil");
        registerButton.setLayoutX(240);
        registerButton.setLayoutY(610);

        root.getChildren().add(text);
        root.getChildren().add(status);
        root.getChildren().add(nama);
        root.getChildren().add(telp);
        root.getChildren().add(member);
        root.getChildren().add(comboBoxA);
        root.getChildren().add(hor_image);
        root.getChildren().add(update_image);
        root.getChildren().add(nameField);
        root.getChildren().add(phoneField);
        root.getChildren().add(comboBox);
        root.getChildren().add(registerButton);
        root.getChildren().add(idText);
        root.getChildren().add(idComboBox);

        setContent(root);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                

                Customer old = customerManager.getCustomerByID(Integer.parseInt(idComboBox.getValue()));
                String selectedOption = comboBox.getValue();
        
                if (old != null) {
                    if (selectedOption.equals("Member")) {
                        Member oldCustomer = new Member(old, name, phone);
                        // Do something with the customer object;
                        customerManager.setCustomerByID(oldCustomer.getId(), oldCustomer.upgradeToVIP(name, phone));
                        
                    } else if (selectedOption.equals("VIP Member")) {
                        VIPMember oldCustomer = new VIPMember(old.getId(), name, phone);
                        // Do something with the customer object;
                        customerManager.setCustomerByID(oldCustomer.getId(), oldCustomer.downgradeToMember());
                    }
                }
                
            }
        });
    }
        
}
