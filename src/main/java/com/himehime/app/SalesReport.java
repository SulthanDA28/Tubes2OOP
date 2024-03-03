package com.himehime.app;

import com.himehime.lib.*;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import java.io.File;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.printing.PDFPageable;

import java.util.ArrayList;

public class SalesReport extends Tab {

    // VBox content = new VBox();
    // for (int i = 1; i <= 20; i++) {
    //     Label label = new Label("Label " + i);
    //     content.getChildren().add(label);
    // }
    // // Create the ScrollPane
    // ScrollPane scrollPane = new ScrollPane();
    // scrollPane.setContent(content);
    // scrollPane.setFitToWidth(true);
    // scrollPane.setFitToHeight(true);
    // scrollPane.setLayoutX(500);

    // // Create a Slider
    // Slider slider = new Slider();
    // slider.setMin(0);
    // slider.setMax(1);
    // slider.setValue(1);
    // scrollPane.vvalueProperty().bindBidirectional(slider.valueProperty());
    public SalesReport(){
        super("Sales Report");
        CustomerManager customerManager = new CustomerManager();
        Text text = new Text();
        text.setText("Sales Report for\nHimehime's Store");
        text.setX(40);
        text.setY(60);
        text.getStyleClass().add("title");

        Text itemName = new Text();
        itemName.setText("Item Name");
        itemName.setX(52);
        itemName.setY(160);
        itemName.getStyleClass().add("sub-title-bold");

        Text itemQuantity = new Text();
        itemQuantity.setText("Quantity");
        itemQuantity.setX(240);
        itemQuantity.setY(160);
        itemQuantity.getStyleClass().add("sub-title-bold");

        Text itemPrice = new Text();
        itemPrice.setText("Price Per Unit");
        itemPrice.setX(440);
        itemPrice.setY(160);
        itemPrice.getStyleClass().add("sub-title-bold");

        // Text total = new Text();
        // total.setText("Total Income");
        // total.setX(640);
        // total.setY(160);
        // total.getStyleClass().add("sub-title-bold");

        VBox content = new VBox();

        // Create the ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setPrefSize(550, 500); // Set preferred size
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setLayoutX(40);
        scrollPane.setLayoutY(180);

        Group root = new Group(scrollPane);

        Scene scene = new Scene(root, 1080, 720, Color.WHITE);
        Image icon = new Image(getClass().getResource("/store.png").toExternalForm());
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Create a TextField for name
        Text nama = new Text();
        nama.setText("Output Location");
        nama.setX(640);
        nama.setY(160);
        nama.getStyleClass().add("sub-title-bold");

        Button nameField = new Button("Enter location for file output");
        nameField.getStyleClass().add("input-field");
        nameField.setLayoutX(640);
        nameField.setLayoutY(200);

        Button printButton = new Button("Print Report");
        printButton.getStyleClass().add("button-kecil");
        printButton.setLayoutX(640);
        printButton.setLayoutY(280);

        root.getChildren().addAll(text, itemName, itemQuantity, itemPrice, printButton, nama, nameField);
        // Set the stage
        setContent(root);
        
        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    ArrayList<FixedBill> transactionList = customerManager.getAllTransaction();
                    content.getChildren().clear();
                    VBox content = createReport(transactionList);
                    scrollPane.setContent(content);
            }
        });
        // nameField.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         DirectoryChooser directoryChooser = new DirectoryChooser();
        //         directoryChooser.setTitle("Select Input Folder");
        //         File selectedDirectory = directoryChooser.showDialog(null);
        //         if (selectedDirectory != null) {
        //             // Process the selected input folder here
        //             String folderPath = selectedDirectory.getAbsolutePath();
        //             // Do something with the folder path
        //             try {
        //                 String filepath = folderPath;
        //                 String content = ReportSystem.printSalesReport(customerManager.getAllTransaction());
        //                 // Create a new PDF document
        //                 PDDocument document = new PDDocument();
            
        //                 // Create a new page
        //                 PDPage page = new PDPage();
            
        //                 // Add the page to the document
        //                 document.addPage(page);
            
        //                 // Create a new content stream for the page
        //                 PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
        //                 // Set the font and font size for the content
        //                 contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            
        //                 // Set the starting position for the content
        //                 contentStream.newLineAtOffset(50, 700);
            
        //                 // Add the content to the page
        //                 contentStream.showText(content);
            
        //                 // Close the content stream
        //                 contentStream.close();
            
        //                 // Save the document to the specified file path
        //                 document.save(filePath);
            
        //                 // Close the document
        //                 document.close();
            
        //                 System.out.println("PDF created successfully at: " + filePath);
        //             } catch (IOException e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        // });
    }

    public VBox createReport(ArrayList<FixedBill> transactionList) {
        VBox content = new VBox();
        
        for (int i = 0; i < transactionList.size(); i++) {
            FixedBill transaction = transactionList.get(i);
            ArrayList<MementoItem> itemList = transaction.getItems();
            for (int j = 0; j < itemList.size(); j++) {
                Item item = itemList.get(j).getItemState();
                String itemDetail = String.format("%-43s %-47s %d", item.getName(), String.format(String.valueOf(item.getQuantity())), item.getPrice());
                Label labelItemDetail = new Label(itemDetail);
                labelItemDetail.getStyleClass().add("sub-title");
                content.getChildren().add(labelItemDetail);
            }
        }

        return content;
    }

}
