package com.himehime.app;

import com.himehime.lib.*;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import javafx.scene.control.Tab;
import java.util.ArrayList;
import javafx.collections.FXCollections;

public class UpdateInventory extends Tab {
    public UpdateInventory() {
        super("Update Inventory");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Create a title text
        Text text = new Text();
        text.setText("Update Inventory for\nHimehime's Store");
        text.getStyleClass().add("title");
        text.setX(90);
        text.setY(140);

        // Create a TextField for name
        Text nameTitle = new Text();
        nameTitle.setText("Name");
        nameTitle.setX(90);
        nameTitle.setY(330);
        nameTitle.getStyleClass().add("sub-title");
        TextField nameField = new TextField();
        nameField.setLayoutX(75);
        nameField.setLayoutY(340);
        nameField.setPromptText("Enter name");
        nameField.getStyleClass().add("input-field");

        // Create a TextField for Category
        Text categoryTitle = new Text();
        categoryTitle.setText("Category");
        categoryTitle.setX(550);
        categoryTitle.setY(330);
        categoryTitle.getStyleClass().add("sub-title");
        TextField CategoryField = new TextField();
        CategoryField.getStyleClass().add("input-field");
        CategoryField.setLayoutX(535);
        CategoryField.setLayoutY(340);
        CategoryField.setPromptText("Enter category");

        // Create a TextField for Stock
        Text stockTitle = new Text();
        stockTitle.setText("Stock");
        stockTitle.setX(90);
        stockTitle.setY(400);
        stockTitle.getStyleClass().add("sub-title");
        TextField StockField = new TextField();
        StockField.getStyleClass().add("input-field");
        StockField.setLayoutX(75);
        StockField.setLayoutY(410);
        StockField.setPromptText("Enter number of Stock");

        // Create a TextField for Price
        Text priceTitle = new Text();
        priceTitle.setText("Price");
        priceTitle.setX(550);
        priceTitle.setY(400);
        priceTitle.getStyleClass().add("sub-title");
        TextField PriceField = new TextField();
        PriceField.getStyleClass().add("input-field");
        PriceField.setLayoutX(535);
        PriceField.setLayoutY(410);
        PriceField.setPromptText("Enter Price");

        // Create a TextField for Picture 
        Text pictureTitle = new Text();
        pictureTitle.setText("Picture");
        pictureTitle.setX(325);
        pictureTitle.setY(470);
        pictureTitle.getStyleClass().add("sub-title");
        Button PictureField = new Button("Select Picture");
        PictureField.getStyleClass().add("input-field");
        PictureField.setLayoutX(310);
        PictureField.setLayoutY(480);

        ArrayList<Item> itemList = Warehouse.getInstance().getInventory();
        ArrayList<String> nameList = new ArrayList<>();
        for (Item item : itemList) {
            if (!item.isRemoved()) {
                nameList.add(item.getName());
            }
        }

        // Create a ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList(nameList));
        comboBox.getStyleClass().add("combo-box");
        comboBox.setLayoutX(75);
        comboBox.setLayoutY(220);
        comboBox.setPromptText("Select an option");

        Button registerButton = new Button("Update");
        registerButton.getStyleClass().add("button-kecil");
        registerButton.setLayoutX(75);
        registerButton.setLayoutY(560);

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("button-kecil-red");
        deleteButton.setLayoutX(195);
        deleteButton.setLayoutY(560);
        
        root.getChildren().add(text);
        root.getChildren().add(comboBox);
        root.getChildren().add(registerButton);
        root.getChildren().add(deleteButton);
        root.getChildren().addAll(StockField, PriceField, PictureField, CategoryField, nameField);
        root.getChildren().addAll(nameTitle, categoryTitle, stockTitle, priceTitle, pictureTitle);
        setContent(root);

        byte[] picture = null;
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
                }
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = comboBox.getValue();
                Item item = Warehouse.getInstance().getItemByName(name);
                item.setName(nameField.getText()).setCategory(CategoryField.getText())
                .setQuantity(Integer.parseInt(StockField.getText()))
                .setPrice(Integer.parseInt(PriceField.getText()))
                .setPicture(picture);
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = comboBox.getValue();
                Item item = Warehouse.getInstance().getItemByName(name);
                item.setRemoved(true);
            }
        });
    }
}
