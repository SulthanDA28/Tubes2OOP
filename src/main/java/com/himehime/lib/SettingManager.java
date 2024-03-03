package com.himehime.lib;

public class SettingManager {
    private String dataStoreLocation;
    private String dataStoreFormat;
    private static SettingManager instance;
    
    // ctor
    private SettingManager() {
        this.dataStoreLocation = "";
        this.dataStoreFormat = "";
    }

    // setter/getter
    public static SettingManager getInstance() {
        if (instance == null) {
            instance = new SettingManager();
        }
        return instance;
    }

    public void setDataStoreLocation(String dataStoreLocation) {
        this.dataStoreLocation = dataStoreLocation;
    }

    public void setDataStoreFormat(String dataStoreFormat) {
        this.dataStoreFormat = dataStoreFormat;
    }

    public String getDataStoreLocation() {
        return this.dataStoreLocation;
    }

    public String getDataStoreFormat() {
        return this.dataStoreFormat;
    }

    // test
    public static void main(String[] args) {
        SettingManager settingManager = SettingManager.getInstance();
        settingManager.setDataStoreLocation("C:\\Users\\himehime\\Desktop\\test");
        settingManager.setDataStoreFormat("json");
        System.out.println(settingManager.getDataStoreLocation());
        System.out.println(settingManager.getDataStoreFormat());
    }
}
