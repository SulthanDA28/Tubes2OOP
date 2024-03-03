package com.himehime.app;

import java.io.IOException;

import javafx.stage.Stage;

interface ApplicationInterface {
    void start(Stage primaryStage) throws IOException;
    void launch();
}
