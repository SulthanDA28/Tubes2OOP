package com.himehime.app;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import java.io.File;
import javafx.scene.control.Tab;

import com.himehime.lib.*;

public class AddInventory extends Tab {
    public AddInventory(){
        super("Add Inventory");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Create a title text
        Text text = new Text();
        text.setText("Add Inventory for\nHimehime's Store");
        text.setX(55);
        text.setY(150);
        text.getStyleClass().add("title");

        // Create a TextField for name
        Text nameTitle = new Text();
        nameTitle.setText("Name");
        nameTitle.setX(55);
        nameTitle.setY(250);
        nameTitle.getStyleClass().add("sub-title");
        TextField nameField = new TextField();
        nameField.setLayoutX(40);
        nameField.setLayoutY(260);
        nameField.setPromptText("Enter name");
        nameField.getStyleClass().add("input-field");

        // Create a TextField for Category
        Text categoryTitle = new Text();
        categoryTitle.setText("Category");
        categoryTitle.setX(515);
        categoryTitle.setY(250);
        categoryTitle.getStyleClass().add("sub-title");
        TextField CategoryField = new TextField();
        CategoryField.setLayoutX(500);
        CategoryField.setLayoutY(260);
        CategoryField.setPromptText("Enter category");
        CategoryField.getStyleClass().add("input-field");

        // Create a TextField for Stock
        Text stockTitle = new Text();
        stockTitle.setText("Stock");
        stockTitle.setX(55);
        stockTitle.setY(320);
        stockTitle.getStyleClass().add("sub-title");
        TextField StockField = new TextField();
        StockField.getStyleClass().add("input-field");
        StockField.setLayoutX(40);
        StockField.setLayoutY(330);
        StockField.setPromptText("Enter number of Stock");

        // Create a TextField for Price
        Text priceTitle = new Text();
        priceTitle.setText("Price");
        priceTitle.setX(515);
        priceTitle.setY(320);
        priceTitle.getStyleClass().add("sub-title");
        TextField PriceField = new TextField();
        PriceField.getStyleClass().add("input-field");
        PriceField.setLayoutX(500);
        PriceField.setLayoutY(330);
        PriceField.setPromptText("Enter Price");

        // Create a TextField for Picture 
        Text pictureTitle = new Text();
        pictureTitle.setText("Picture");
        pictureTitle.setX(290);
        pictureTitle.setY(390);
        pictureTitle.getStyleClass().add("sub-title");
        Button PictureField = new Button("Select Picture");
        PictureField.getStyleClass().add("input-field");
        PictureField.setLayoutX(275);
        PictureField.setLayoutY(400);
        PictureField.getStyleClass().add("input-field");

        Button registerButton = new Button("Add Inventory");
        registerButton.getStyleClass().add("button-kecil");
        registerButton.setLayoutX(40);
        registerButton.setLayoutY(480);
        
        root.getChildren().add(text);
        
        root.getChildren().add(registerButton);
        root.getChildren().addAll(StockField, PriceField, PictureField, CategoryField, nameField);
        root.getChildren().addAll(nameTitle, categoryTitle, stockTitle, priceTitle, pictureTitle);

        setContent(root);

        Item item = new Item();
        PictureField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    byte[] picture = ImageAdaptor.readImageBytes(selectedFile);
                    item.setPicture(picture);
                }
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField.getText();
                String category = CategoryField.getText();
                int stock = Integer.parseInt(StockField.getText());
                int price = Integer.parseInt(PriceField.getText());        
                item.setName(name).setCategory(category).setQuantity(stock).setPrice(price);
                Warehouse warehouse =  Warehouse.getInstance();
                warehouse.addItem(item);
                System.out.println(warehouse.getInventory());
            }
        });
        
    }
}
