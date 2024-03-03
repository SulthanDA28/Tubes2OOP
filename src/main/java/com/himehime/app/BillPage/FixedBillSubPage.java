package com.himehime.app.BillPage;

import com.himehime.lib.FixedBill;
import com.himehime.lib.Item;
import com.himehime.lib.MementoItem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FixedBillSubPage {
    private FixedBill fixedBill;

    public FixedBillSubPage(FixedBill fixedBill) {
        this.fixedBill = fixedBill;
    }

    public Region createRegion() {
        VBox billPrintContainer = new VBox();
        billPrintContainer.setPadding(new Insets(10, 10, 10, 10));
        billPrintContainer.setSpacing(10);

        Label billLabel = new Label("Transaksi berhasil!");
        billPrintContainer.getChildren().add(billLabel);

        Label customerID = new Label("ID Pelanggan: " + fixedBill.getCustomerID());
        billPrintContainer.getChildren().add(customerID);

        GridPane billColumnHeader = createFixedBillColumn("Qty","Nama Item","Harga Satuan","Harga Total");
        billPrintContainer.getChildren().add(billColumnHeader);

        ScrollPane billItemScrollPane = new ScrollPane();
        billItemScrollPane.setFitToWidth(true);
        billItemScrollPane.setFitToHeight(true);
        billItemScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ListView<GridPane> listView = new ListView<>();
        for (MementoItem mementoItem : fixedBill.getItems()) {
            Item item = mementoItem.getItemState();
            listView.getItems().add(createFixedBillColumn(Integer.toString(item.getQuantity()),item.getName(),"Rp. "+item.getPrice(),"Rp, " + item.getPrice()*item.getQuantity()));
        }
        billItemScrollPane.setContent(listView);
        billPrintContainer.getChildren().add(billItemScrollPane);

        GridPane infoBiaya = new GridPane();
        infoBiaya.setHgap(10);
        infoBiaya.setVgap(10);
        infoBiaya.setPadding(new Insets(10, 10, 10, 10));
        infoBiaya.setPrefWidth(20);
        ColumnConstraints columnC = new ColumnConstraints();
        columnC.setPercentWidth(50);
        infoBiaya.getColumnConstraints().add(columnC);
        infoBiaya.getColumnConstraints().add(columnC);
        Label subTotalLabel = new Label("Subtotal");
        Label diskonLabel = new Label("Diskon");
        Label totalLabel = new Label("Total");
        Label subTotalValue = new Label("Rp. " + fixedBill.getSubTotalPrice());
        Label diskonValue = new Label("Rp. " + fixedBill.getDiscount());
        Label totalValue = new Label("Rp. " + (fixedBill.getSubTotalPrice() - fixedBill.getDiscount()));
        infoBiaya.add(subTotalLabel, 0, 0);
        infoBiaya.add(subTotalValue, 1, 0);
        infoBiaya.add(diskonLabel, 0, 1);
        infoBiaya.add(diskonValue, 1, 1);
        infoBiaya.add(totalLabel, 0, 2);
        infoBiaya.add(totalValue, 1, 2);
        billPrintContainer.getChildren().add(infoBiaya);

        Button printBillButton = new Button("Print Bill");
        printBillButton.setAlignment(Pos.CENTER);
        printBillButton.setPrefSize(Double.MAX_VALUE, 50);
        billPrintContainer.getChildren().add(printBillButton);

        billPrintContainer.setMaxSize(400, 600);
        return billPrintContainer;
    }

    private GridPane createFixedBillColumn(String a, String b, String c, String d){
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(340);
        gridPane.setMinWidth(340);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints column1C = new ColumnConstraints();
        ColumnConstraints column2C = new ColumnConstraints();
        ColumnConstraints column3C = new ColumnConstraints();
        ColumnConstraints column4C = new ColumnConstraints();
        column1C.setPercentWidth(10);
        column2C.setPercentWidth(40);
        column3C.setPercentWidth(25);
        column4C.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(column1C, column2C, column3C, column4C);
        Label label1 = new Label(a);
        label1.setWrapText(true);
        Label label2 = new Label(b);
        label2.setWrapText(true);
        Label label3 = new Label(c);
        label3.setWrapText(true);
        Label label4 = new Label(d);
        label4.setWrapText(true);
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 1, 0);
        gridPane.add(label3, 2, 0);
        gridPane.add(label4, 3, 0);
        return gridPane;
    }
}
