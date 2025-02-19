package com.example.finalproject.LukaGenerated.SearchStuff;

import java.io.Serializable;

public class SearchObject implements Serializable {
    private String sectionName;
    private String itemName;

    public SearchObject(String sectionName, String itemName) {
        this.sectionName = sectionName;
        this.itemName = itemName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}