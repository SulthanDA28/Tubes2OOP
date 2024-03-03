package com.himehime.app;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;

import java.io.File;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.himehime.lib.*;

public class SettingsMenu extends Tab {
    public SettingsMenu(CustomerManager customerManager){
        super("Settings");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image settings_img = new Image(getClass().getResource("/settings_img.png").toExternalForm());
        ImageView settings_image = new ImageView(settings_img);
        settings_image.setX(270);
        settings_image.setY(80);
        settings_image.setFitHeight(171);
        settings_image.setFitWidth(240);

        // Create a title text
        Text text = new Text();
        text.setText("Settings");
        text.getStyleClass().add("title");
        text.setX(75);
        text.setY(220);

        // Create a TextField for name
        Text path = new Text();
        path.setText("Data Path");
        path.setX(75);
        path.setY(330);
        path.getStyleClass().add("sub-title");
        Button pathField = new Button("Select Path");
        pathField.getStyleClass().add("input-field");
        pathField.setLayoutX(60);
        pathField.setLayoutY(340);
       
        Text type = new Text();
        type.setText("Data Type");
        type.setX(75);
        type.setY(250);
        type.getStyleClass().add("sub-title");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("combo-box");
        comboBox.getItems().addAll("(.json)", "(.xml)", "(.obj)");
        comboBox.setLayoutX(60);
        comboBox.setLayoutY(260);
        comboBox.setPromptText("Select your data type");

        Button saveButton = new Button("Save Settings");
        saveButton.getStyleClass().add("button-kecil");
        saveButton.setLayoutX(60);
        saveButton.setLayoutY(400);

        root.getChildren().addAll(text, path, pathField, comboBox, type, saveButton);
        root.getChildren().add(settings_image);

        setContent(root);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the Customer class
                String dataType = comboBox.getValue();
                String dataPath = pathField.getText();
                System.out.println(dataType);
                System.out.println(dataPath);
            }
        });

        pathField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Data File");
                SettingManager settingManager = SettingManager.getInstance();
                if(type.getText().equals(".json")){
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                    fileChooser.setInitialDirectory(new File("src/main/resources/data/json"));
                }
                else if(type.getText().equals(".xml")){
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
                    fileChooser.setInitialDirectory(new File("src/main/resources/data/xml"));
                }
                else if(type.getText().equals(".obj")){
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("OBJ Files", "*.obj"));
                    fileChooser.setInitialDirectory(new File("src/main/resources/data/obj"));
                }
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // do something with the selected file
                    pathField.setText(selectedFile.getPath());
                    settingManager.setDataStoreLocation(selectedFile.getPath());
                    settingManager.setDataStoreFormat(type.getText());
                }
            }
        });

    }
}
