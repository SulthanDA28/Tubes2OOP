package com.himehime.app.BillPage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter.Change;

import java.util.Date;
import java.util.function.UnaryOperator;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import com.himehime.lib.Bill;
import com.himehime.lib.Customer;
import com.himehime.lib.CustomerManager;
import com.himehime.lib.FixedBill;
import com.himehime.lib.Item;
import com.himehime.lib.Member;
import com.himehime.lib.Warehouse;

import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class BillPage extends Tab {
    private Bill bill;
    private Warehouse warehouse;
    private FlowPane itemFlowPane;
    private TextField searchField, minPriceField, maxPriceField;
    private ComboBox<String> category;
    private ListView<GridPane> billItemListView;
    private CheckBox usePointCheckbox;
    private Label subtotalValueLabelLabel;
    private Label diskonValueLabel;
    private Label totalValueLabel;
    private Label pointAcquiredValueLabel;
    private Label memberPointLabel;
    private int total;
    private int discount;
    private int subTotal;
    private Customer customer;

    public BillPage(CustomerManager customerManager, Bill bill) {
        super("Bill");
        this.bill = bill;
        this.warehouse = Warehouse.getInstance();
        this.customer = null;
    
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1080, 720, Color.WHITE);

        // set up main container
        BorderPane onGoingBillContainer = new BorderPane();
        StackPane leftContainer = new StackPane(); // Use a StackPane to contain the left area

        // set up left area
        BorderPane leftArea = new BorderPane();
        leftContainer.getChildren().add(leftArea);
        onGoingBillContainer.setCenter(leftContainer);
        
        // set up right area
        BorderPane rightArea = new BorderPane();
        rightArea.setMinWidth(300);
        rightArea.setMaxWidth(300);
        onGoingBillContainer.setRight(rightArea);

        // left area content
        HBox searchBar = new HBox();
        searchBar.setPadding(new Insets(10, 10, 10, 10));
        searchBar.setSpacing(10);
        // search bar
        searchField = new TextField();
        searchField.setPromptText("Nama Barang");
        searchField.setPrefWidth(100);
        searchBar.getChildren().add(searchField);

        Label categoryLabel = new Label("Kategori: ");
        searchBar.getChildren().add(categoryLabel);

        category = new ComboBox<String>();
        category.setValue("Semua");
        category.getItems().add("Semua");
        
        for (String cat : warehouse.getAllCategory()) {
            category.getItems().add(cat);
        }
        searchBar.getChildren().add(category);

        Label minPriceLabel = new Label("Min. Price");
        searchBar.getChildren().add(minPriceLabel);

        minPriceField = new TextField();
        minPriceField.setPromptText("10");
        minPriceField.setPrefWidth(60);
        searchBar.getChildren().add(minPriceField);
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("|[1-9][0-9]*")) { 
                return change;
            }
            return null;
        };
        minPriceField.setTextFormatter(new TextFormatter<>(integerFilter));

        Label maxPriceLabel = new Label("Max. Price");
        searchBar.getChildren().add(maxPriceLabel);

        maxPriceField = new TextField();
        maxPriceField.setPromptText("10");
        maxPriceField.setPrefWidth(60);
        maxPriceField.setTextFormatter(new TextFormatter<>(integerFilter));
        searchBar.getChildren().add(maxPriceField);
        leftArea.setTop(searchBar);

        itemFlowPane = new FlowPane();
        itemFlowPane.setHgap(10);
        itemFlowPane.setVgap(10);
        setItemFlow();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(itemFlowPane);
        scrollPane.setPadding(new Insets(10, 10, 0, 10));
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(200, 200);
        stackPane.getChildren().add(scrollPane);
        StackPane.setAlignment(scrollPane, Pos.CENTER);
        leftArea.setCenter(stackPane);

        ChangeListener<String> searchListener = (observable, oldValue, newValue) -> {
            setItemFlow();
        };

        searchField.textProperty().addListener(searchListener);
        minPriceField.textProperty().addListener(searchListener);
        maxPriceField.textProperty().addListener(searchListener);
        category.valueProperty().addListener(searchListener);

        // right area content
        // top area
        VBox rightTopArea = new VBox();
        rightTopArea.setPadding(new Insets(10, 10, 10, 10));
        rightTopArea.setSpacing(10);
        Label billLabel = new Label("Bill");
        ScrollPane billItemScrollPane = new ScrollPane();
        billItemScrollPane.setFitToWidth(true);
        billItemScrollPane.setFitToHeight(true);
        billItemScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        billItemListView = new ListView<>();
        updateBillItemList();
        scrollPane.setFitToWidth(true);
        billItemScrollPane.setContent(billItemListView);

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
        Label DiskonLabel = new Label("Diskon");
        Label totalLabel = new Label("Total");
        subtotalValueLabelLabel = new Label("Rp. 0");
        diskonValueLabel = new Label("Rp. 0");
        totalValueLabel = new Label("Rp. 0");
        infoBiaya.add(subTotalLabel, 0, 0);
        infoBiaya.add(subtotalValueLabelLabel, 1, 0);
        infoBiaya.add(DiskonLabel, 0, 1);
        infoBiaya.add(diskonValueLabel, 1, 1);
        infoBiaya.add(totalLabel, 0, 2);
        infoBiaya.add(totalValueLabel, 1, 2);
        rightTopArea.getChildren().addAll(billLabel, billItemScrollPane, infoBiaya);
        rightArea.setCenter(rightTopArea);

        // bottom area
        VBox rightBottomArea = new VBox();
        
        rightBottomArea.setPadding(new Insets(10, 10, 10, 10));
        rightBottomArea.setSpacing(10);
        memberPointLabel = new Label();
        rightBottomArea.getChildren().add(memberPointLabel);
        pointAcquiredValueLabel = new Label();
        rightBottomArea.getChildren().add(pointAcquiredValueLabel);

        usePointCheckbox = new CheckBox();
        usePointCheckbox.setText("Gunakan Poin");
        rightBottomArea.getChildren().add(usePointCheckbox);

        ComboBox<String> customerNameSelectBox = new ComboBox<String>();
        customerNameSelectBox.setPromptText("Nama Member");
        customerNameSelectBox.getItems().add("Pelanggan baru");
        for (Customer customer : customerManager.getCustomerList()) {
            if (customer instanceof Member) {
                Member member = (Member) customer;
                customerNameSelectBox.getItems().add(member.getName());
            }
        }
        customerNameSelectBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal != "Pelanggan baru") {
                for (Customer customer : customerManager.getCustomerList()) {
                    if (customer instanceof Member) {
                        Member member = (Member) customer;
                        if (member.getName().equals(newVal)) {
                            this.customer = member;
                            break;
                        }
                    }
                }
            } else {
                this.customer = null;
            }
            updateBillPriceInfo();
        });

        rightBottomArea.getChildren().add(customerNameSelectBox);
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setPrefSize(Double.MAX_VALUE, 50);
        checkoutButton.setAlignment(Pos.CENTER);
        rightBottomArea.getChildren().add(checkoutButton);
        rightArea.setBottom(rightBottomArea);
        updateBillPriceInfo();

        root.getChildren().add(onGoingBillContainer);

        checkoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onGoingBillContainer.setDisable(true);
                onGoingBillContainer.setOpacity(0.8);
                if (customer == null) {
                    customer = new Customer(customerManager.getCustomerList().size()+1);
                    customerManager.addCustomer(customer);
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();
                FixedBill fixedBill = new FixedBill(bill,customer.getId(),formatter.format(date),discount);
                customer.addTransaction(fixedBill);
                if (customer instanceof Member){
                    Member member = (Member) customer;
                    if (usePointCheckbox.isSelected())
                        member.setPoint(0);
                    member.addPoint((int) Math.floor(total*0.01));
                }
                FixedBillSubPage fixedBillPrint = new FixedBillSubPage(fixedBill);
                Region fixedBillRegion = fixedBillPrint.createRegion();
                root.getChildren().add(fixedBillRegion);
                StackPane.setAlignment(fixedBillRegion, Pos.CENTER);
            }
        });

        setContent(root);
    }

    private void setItemFlow(){
        itemFlowPane.getChildren().clear();
        for (Item item : warehouse.getInventory()) {
            if ((minPriceField.getText() == "" || item.getPrice() >= Integer.parseInt(minPriceField.getText())) &&
                (maxPriceField.getText() == "" || item.getPrice() <= Integer.parseInt(maxPriceField.getText())) &&
                (searchField.getText() == "" || item.getName().startsWith(searchField.getText())) &&
                (category.getValue() == "Semua" || item.getCategory().equals(category.getValue()))) {
                Button itemPane = new Button();
                VBox itemBox = new VBox();
                Image img = new Image(new ByteArrayInputStream(item.getPicture()));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(100);
                imgView.setFitWidth(100);
                Text text = new Text(item.getName() + "\n Harga : " + item.getPrice());
                text.setTextAlignment(TextAlignment.CENTER);
                itemBox.getChildren().addAll(imgView, text);
                itemBox.setAlignment(Pos.CENTER);
                itemPane.setGraphic(itemBox);
                itemPane.setUserData(item);
                itemFlowPane.getChildren().add(itemPane);
                itemPane.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Item Witem = (Item) itemPane.getUserData();
                        for (Item item : bill.getItems()) {
                            if (item.getName().equals(Witem.getName())) {
                                item.setQuantity(item.getQuantity() + 1);
                                updateBillItemList();
                                updateBillPriceInfo();
                                return;
                            }
                        }
                        bill.addItem(Witem,1);
                        updateBillItemList();
                        updateBillPriceInfo();
                    }
                });
            }
        }
    }

    private void updateBillItemList(){
        billItemListView.getItems().clear();
        for (Item item : bill.getItems()) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(10, 10, 10, 10));
            gridPane.setMinWidth(240);
            gridPane.setMaxWidth(240);
            ColumnConstraints columnC = new ColumnConstraints();
            columnC.setPercentWidth(50);

            gridPane.getColumnConstraints().add(columnC);
            gridPane.getColumnConstraints().add(columnC);
            Label nameLabel = new Label(item.getName());
            nameLabel.setWrapText(true);
            Label priceLabel = new Label("Rp. " + item.getPrice());
            priceLabel.setWrapText(true);
            Spinner<Integer> quantitySpinner = new Spinner<Integer>();
            quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
            quantitySpinner.getValueFactory().setValue(item.getQuantity());
            quantitySpinner.valueProperty().addListener((obs,oldValue,newValue)
                    -> {item.setQuantity(newValue); updateBillPriceInfo();});
            gridPane.add(nameLabel, 0, 0);
            gridPane.add(priceLabel, 1, 0);
            gridPane.add(quantitySpinner, 1, 1);
            billItemListView.getItems().add(gridPane);
        }
    }

    private void updateBillPriceInfo(){
        subTotal = 0;
        for (Item item : bill.getItems()) {
            subTotal += item.getPrice() * item.getQuantity();
        }
        discount = 0;
        if (customer != null && customer instanceof Member) {
            Member member = (Member) customer;
            if (usePointCheckbox.isSelected()) {
                discount = member.getDiscount(subTotal, member.getPoint());
            } else {
                discount = member.getDiscount(subTotal, 0);
            }
        }
        total = subTotal - discount;
        subtotalValueLabelLabel.setText("Rp. " + subTotal);
        diskonValueLabel.setText("Rp. " + discount);
        totalValueLabel.setText("Rp. " + total);
        updatePointLabels();
    }

    private void updatePointLabels(){
        if (customer != null && customer instanceof Member) {
            Member member = (Member) customer;
            memberPointLabel.setText("Poin saat ini : " + member.getPoint());
            pointAcquiredValueLabel.setText("Poin yang akan didapat : " + Math.round(total*0.01));
            memberPointLabel.setVisible(true);
            pointAcquiredValueLabel.setVisible(true);
        } else {
            memberPointLabel.setVisible(false);
            pointAcquiredValueLabel.setVisible(false);
        }
    }
}
