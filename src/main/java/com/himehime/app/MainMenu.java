package com.himehime.app;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;

public class MainMenu extends Tab{

    public MainMenu() {

        super("Main Menu");
        Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Create image
        Image awal_img = new Image(getClass().getResource("/icon_awal.png").toExternalForm());
        ImageView awal_image = new ImageView(awal_img);
        awal_image.setX(250);
        awal_image.setY(120);
        awal_image.setFitHeight(578);
        awal_image.setFitWidth(720);

        Label dayDateLabel = new Label();
        Label timeLabel = new Label();
        Label storeLabel = new Label("Himehime's Store");
        Label authorLabel = new Label("Authored by:");
        dayDateLabel.getStyleClass().add("sub-title");
        dayDateLabel.setLayoutX(50);
        dayDateLabel.setLayoutY(50);
        timeLabel.getStyleClass().add("time");
        timeLabel.setLayoutX(50);
        timeLabel.setLayoutY(52);
        storeLabel.getStyleClass().add("title");
        storeLabel.setLayoutX(50);
        storeLabel.setLayoutY(150);
        authorLabel.getStyleClass().add("sub-title-bold");
        authorLabel.setLayoutX(50);
        authorLabel.setLayoutY(200);

        root.getChildren().addAll(dayDateLabel, timeLabel);

        Runnable DateTimeUpdater = new Runnable() {
            private final DateFormat dayDateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
            private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            @Override
            public void run() {
                try {
                    while (true) {
                        Date now = new Date();
                        Platform.runLater(() -> {
                            dayDateLabel.setText(dayDateFormat.format(now));
                            timeLabel.setText(timeFormat.format(now));
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread dateTimeThread = new Thread(DateTimeUpdater);
        dateTimeThread.setDaemon(true);
        dateTimeThread.start();

        root.getChildren().add(storeLabel);
        root.getChildren().add(authorLabel);

        Label name1 = new Label("13521118 / Ahmad Ghulam Ilham");
        Label name2 = new Label("13521152 / Muhammad Naufal Nalendra");
        Label name3 = new Label("13521158 / Muhammad Dhiwaul Akbar");
        Label name4 = new Label("13521159 / Sulthan Dzaky Alfaro");
        Label name5 = new Label("13521166 / Mohammad Rifqi Farhansyah");
        Label name6 = new Label("13521169 / Muhammad Habibi Husni");
        name1.setLayoutX(50);
        name1.setLayoutY(220);
        name1.getStyleClass().add("sub-title");
        name2.setLayoutX(50);
        name2.setLayoutY(240);
        name2.getStyleClass().add("sub-title");
        name3.setLayoutX(50);
        name3.setLayoutY(260);
        name3.getStyleClass().add("sub-title");
        name4.setLayoutX(50);
        name4.setLayoutY(280);
        name4.getStyleClass().add("sub-title");
        name5.setLayoutX(50);
        name5.setLayoutY(300);
        name5.getStyleClass().add("sub-title");
        name6.setLayoutX(50);
        name6.setLayoutY(320);
        name6.getStyleClass().add("sub-title");

        root.getChildren().add(name1);
        root.getChildren().add(name2);
        root.getChildren().add(name3);
        root.getChildren().add(name4);
        root.getChildren().add(name5);
        root.getChildren().add(name6);

        root.getChildren().add(awal_image);

        setContent(root);
    }
}

