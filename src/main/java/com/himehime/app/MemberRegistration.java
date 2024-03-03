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

import com.himehime.lib.*;

public class MemberRegistration extends Tab{
    public MemberRegistration(CustomerManager customerManager) {
        super("Member Registration");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        // Create image
        Image regist_img = new Image(getClass().getResource("/registImage.png").toExternalForm());
        ImageView regist_image = new ImageView(regist_img);
        regist_image.setX(250);
        regist_image.setY(120);
        regist_image.setFitHeight(720);
        regist_image.setFitWidth(1080);

        // Create image
        Image hor_img = new Image(getClass().getResource("/hor.png").toExternalForm());
        ImageView hor_image = new ImageView(hor_img);
        hor_image.setX(0);
        hor_image.setY(650);
        hor_image.setFitHeight(293);
        hor_image.setFitWidth(1280);

        // Create a title text
        Text text = new Text();
        text.setText("Register Membership for\nHimehime's Store");
        text.getStyleClass().add("title");
        text.setX(75);
        text.setY(180);

        // Create a ComboBox
        Text status = new Text();
        status.setText("Status");
        status.setX(75);
        status.setY(280);
        status.getStyleClass().add("sub-title");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("combo-box");
        comboBox.getItems().addAll("Customer", "Member", "VIP Member");
        comboBox.setLayoutX(60);
        comboBox.setLayoutY(290);
        comboBox.setPromptText("Select an option");

        // Create a TextField for name
        Text nama = new Text();
        nama.setText("Nama");
        nama.setX(75);
        nama.setY(370);
        nama.getStyleClass().add("sub-title");
        TextField nameField = new TextField();
        nameField.getStyleClass().add("input-field");
        nameField.setLayoutX(60);
        nameField.setLayoutY(380);
        nameField.setPromptText("Enter your name");

        // Create a TextField for phone number
        Text telp = new Text();
        telp.setText("Nomor Telepon");
        telp.setX(75);
        telp.setY(450);
        telp.getStyleClass().add("sub-title");
        TextField phoneField = new TextField();
        phoneField.getStyleClass().add("input-field");
        phoneField.setLayoutX(60);
        phoneField.setLayoutY(460);
        phoneField.setPromptText("+62-XXXX-XXXX-XXX");

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button-kecil");
        registerButton.setLayoutX(250);
        registerButton.setLayoutY(530);
        
        root.getChildren().add(text);
        root.getChildren().add(status);
        root.getChildren().add(nama);
        root.getChildren().add(telp);
        root.getChildren().add(hor_image);
        root.getChildren().add(regist_image);
        root.getChildren().add(nameField);
        root.getChildren().add(phoneField);
        root.getChildren().add(comboBox);
        root.getChildren().add(registerButton);

        setContent(root);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the Customer class
                Member customer = new Member(customerManager.getCustomerList().size() + 1, nameField.getText(), phoneField.getText());
                customerManager.addCustomer(customer);
                // Do something with the customer object;
                
                // Example: Print the customer details
                System.out.println("Customer name: " + customer.getName());
                System.out.println("Phone number: " + customer.getPhone());
                System.out.println(customerManager.getCustomerList().get(0));
            }
        });
    }
}
