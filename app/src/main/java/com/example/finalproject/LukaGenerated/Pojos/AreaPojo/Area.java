package com.example.finalproject.LukaGenerated.Pojos.AreaPojo;


import com.google.gson.annotations.SerializedName;

public class Area {

    @SerializedName("strArea")
    private String areaName;

    // Constructor
    public Area(String areaName) { this.areaName = areaName; }

    // Getter
    public String getAreaName() { return areaName; }

    // Setter
    public void setAreaName(String areaName) { this.areaName = areaName; }
}
