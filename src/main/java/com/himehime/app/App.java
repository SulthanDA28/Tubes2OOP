package com.himehime.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import com.himehime.lib.CustomerManager;
import com.himehime.lib.JSONAdapter;
import com.himehime.lib.Member;
import com.himehime.lib.SettingManager;
import com.himehime.lib.VIPMember;
import com.himehime.lib.Warehouse;
import com.himehime.lib.Bill;
import com.himehime.lib.Customer;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.himehime.app.BillPage.*;;
public class App extends Application {
    private CustomerManager customerManager;
    private ArrayList<Bill> billList;
    private SettingManager settingManager;
    private Warehouse warehouse;

    @Override
    public void start(Stage primaryStage) {
        JSONAdapter jsonAdapter = new JSONAdapter();
        jsonAdapter.loadSettingManager();
        this.settingManager = SettingManager.getInstance();
        this.warehouse = Warehouse.getInstance();
        warehouse.setInventory(jsonAdapter.readWarehouseItems(settingManager.getDataStoreLocation()+"warehouse.json"));
        customerManager = jsonAdapter.readCustomerList(settingManager.getDataStoreLocation()+"customer.json");
        billList = jsonAdapter.readBill(settingManager.getDataStoreLocation()+"bill.json"); 
        // Load CSS file
        String css = getClass().getResource("/application.css").toExternalForm();
        // Create the TabPane
        TabPane tabPane = new TabPane();

        MainMenu mainMenuTab = new MainMenu();
        tabPane.getTabs().add(mainMenuTab);

        Label labelMenuBar = new Label("Menu Bar");
        EventHandler<ActionEvent> menuBarEventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                labelMenuBar.setText(((MenuItem)e.getSource()).getText() + " selected");
            }
        };
        
        Menu menu1 = createMenu("Customer", new String[]{"Member Registration", "Update Membership", "Riwayat"}, menuBarEventHandler);
        Menu menu2 = createMenu("Inventory", new String[]{"Add Inventory", "Update Inventory"}, menuBarEventHandler);
        Menu menu3 = createMenu("Sales", new String[]{"Sales Report", "Bill"}, menuBarEventHandler);
        Menu menu4 = createMenu("Settings", new String[]{"Settings"}, menuBarEventHandler);
  
        // create a menubar
        MenuBar menuBar = new MenuBar();
  
        // add menu to menubar
        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);
        menuBar.getMenus().add(menu3);
        menuBar.getMenus().add(menu4);

        menu1.setOnAction(event -> {
            String selectedMenu = ((MenuItem) event.getTarget()).getText();
            Tab selectedTab = null;
            
            switch(selectedMenu) {
                case "Member Registration":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof MemberRegistration) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new MemberRegistration(this.customerManager);
                        tabPane.getTabs().add(selectedTab);
                    }
                    break;
                case "Update Membership":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof UpdateMembership) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new UpdateMembership(this.customerManager);
                        tabPane.getTabs().add(selectedTab);
                    }
                    break;
                case "Riwayat":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof Riwayat) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new Riwayat();
                        tabPane.getTabs().add(selectedTab);
                    }
                    break;
            }
            
            if (selectedTab != null) {
                tabPane.getSelectionModel().select(selectedTab);
            }
            this.setPageStyle(tabPane);
        });

        menu2.setOnAction(event -> {
            String selectedMenu = ((MenuItem) event.getTarget()).getText();
            Tab selectedTab = null;
            
            switch(selectedMenu) {
                case "Add Inventory":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof AddInventory) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new AddInventory();
                        tabPane.getTabs().add(selectedTab);
                    }
                    break;
                case "Update Inventory":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof UpdateInventory) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new UpdateInventory();
                        tabPane.getTabs().add(selectedTab);
                    }
                    break;
            }
            
            if (selectedTab != null) {
                tabPane.getSelectionModel().select(selectedTab);
            }
            this.setPageStyle(tabPane);
        });

        menu3.setOnAction(event -> {
            String selectedMenu = ((MenuItem) event.getTarget()).getText();
            Tab selectedTab = null;
            
            switch(selectedMenu) {
                case "Sales Report":
                    for (Tab tab : tabPane.getTabs()) {
                        if (tab instanceof SalesReport) {
                            selectedTab = tab;
                            break;
                        }
                    }
                    if (selectedTab == null) {
                        selectedTab = new SalesReport();
                        this.setSinglePageStyle(selectedTab);
                        tabPane.getTabs().add(selectedTab);
                    }
                    
                    break;
                case "Bill":
                    Bill newBill = new Bill();
                    this.billList.add(newBill);
                    selectedTab = new BillPage(this.customerManager, newBill);
                    tabPane.getTabs().add(selectedTab);
                    break;
            }
            
            if (selectedTab != null) {
                tabPane.getSelectionModel().select(selectedTab);
            }
            
        });

        menu4.setOnAction(event -> {
            Tab selectedTab = null;
            
            for (Tab tab : tabPane.getTabs()) {
                if (tab instanceof SettingsMenu) {
                    selectedTab = tab;
                    break;
                }
            }
            if (selectedTab == null) {
                selectedTab = new SettingsMenu(this.customerManager);
                tabPane.getTabs().add(selectedTab);
                tabPane.getSelectionModel().select(selectedTab);
            }
            this.setPageStyle(tabPane);
        });

        this.setPageStyle(tabPane);

        // Create the layout
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        vbox.getChildren().add(tabPane);
        
        // Create the Scene with the TabPane
        Scene scene = new Scene(vbox, 1080, 720, Color.WHITE);
        scene.getStylesheets().add(css);
        // Set the Scene on the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("HimeHime");
        Image icon = new Image(getClass().getResourceAsStream("/store.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.setResizable(false);

        // Show the Stage
        primaryStage.show();
    }
    public Menu createMenu(String menuName, String[] itemName, EventHandler<ActionEvent> event) {
        Menu menu = new Menu(menuName);
        for (String name : itemName) {
            MenuItem item = new MenuItem(name);
            item.setOnAction(event);
            menu.getItems().add(item);
        }
        return menu;
    }
    public void setPageStyle(TabPane tabPane) {
        Insets contentPadding = new Insets(10);
        Color backgroundColor = Color.WHITE;
        for (Tab tab : tabPane.getTabs()) {
            StackPane contentPane = new StackPane();
            contentPane.setPadding(contentPadding);
            contentPane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
            contentPane.getChildren().add(tab.getContent());
            tab.setContent(contentPane);
        }
    }
    public void setSinglePageStyle(Tab tab) {
        Insets contentPadding = new Insets(10);
        Color backgroundColor = Color.WHITE;
        StackPane contentPane = new StackPane();
        contentPane.setPadding(contentPadding);
        contentPane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        contentPane.getChildren().add(tab.getContent());
        tab.setContent(contentPane);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        JSONAdapter jsonAdapter = new JSONAdapter();
        jsonAdapter.writeBill(billList, settingManager.getDataStoreLocation()+"\\bill.json");
        jsonAdapter.writeCustomerList(customerManager, settingManager.getDataStoreLocation()+"\\customer.json");
        jsonAdapter.writeWarehouse(Warehouse.getInstance(), settingManager.getDataStoreLocation()+"\\warehouse.json");
        jsonAdapter.writeSettingManager();
        System.out.println("Stage is closing");
    }
}
