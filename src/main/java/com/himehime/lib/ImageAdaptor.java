package com.himehime.lib;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;

public class ImageAdaptor {
    public static byte[] readImageBytes(Path imagePath) {
        try {
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            System.out.println("Error reading image bytes");
            return null;
        }
    }

    public static byte[] readImageBytes(File imageFile) {
        try {
            return Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            System.out.println("Error reading image bytes");
            return null;
        }
    }

    public static Image readImageFromBytes(byte[] imageBytes) {
        try {
            return new Image (new ByteArrayInputStream(imageBytes));
        } catch (Exception e) {
            System.out.println("Error reading image from bytes");
            return null;
        }
    }

    public static BufferedImage readBufferedImageFromBytes(byte[] imageBytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (Exception e) {
            System.out.println("Error reading image from bytes");
            return null;
        }
    }

    public static void writeImageToFile(BufferedImage image, Path imagePath) {
        try {
            Files.createDirectories(imagePath.getParent());
        } catch (IOException e) {
            System.out.println("Error creating directories");
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] imageBytes = readImageBytes(Path.of("src/main/resources/foto.jpg"));
        System.out.println(imageBytes);
        BufferedImage image = readBufferedImageFromBytes(imageBytes);
        System.out.println(image);
        writeImageToFile(image, Path.of("src/main/resources/foto2.jpg"));
        System.out.println("Done");
    }
}
