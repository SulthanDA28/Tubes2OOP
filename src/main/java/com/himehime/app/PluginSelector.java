package com.himehime.app;

import com.himehime.lib.*;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginSelector extends Application {
    private static final List<FileLoader> fileLoaders = new ArrayList<>();

    public static void main(String[] args) {
        // launch a JavaFX Application
        Application.launch(PluginSelector.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // create a pop-up panel to select a .jar file
        File selectedFile = PluginSelector.selectFile(primaryStage);
        List<String> names = Arrays.asList(selectedFile.list());
        names.forEach(name -> {
            try {
                final Class<?> c = Class.forName(name);
                final List<Class<?>> interfaces = Arrays.asList(c.getInterfaces());
                interfaces.forEach(itf -> {
                    if (itf.getName().equals("FileLoader")) {
                        try {
                            Constructor<?> constructor = c.getDeclaredConstructor();
                            FileLoader fl = (FileLoader) constructor.newInstance();
                            fileLoaders.add(fl);
                        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                            // tidak bisa instansiasi, plugin tidak usah diproses
                        }
                    } // else, abaikan kelas ini
                });
            } catch (ClassNotFoundException ex) {
                // abaikan file tsb
            }
        });
    }
    
    public static File selectFile(Stage stage) {
        // create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select .jar file");
    
        // set initial directory to root folder
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    
        // limit file chooser to only show .jar files
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JAR Files", "*.jar")
        );
    
        // show the file chooser and wait for user to select file
        File selectedFile = fileChooser.showOpenDialog(stage);
    
        // return the selected file, or null if user cancelled the selection
        return selectedFile;
    }    
}