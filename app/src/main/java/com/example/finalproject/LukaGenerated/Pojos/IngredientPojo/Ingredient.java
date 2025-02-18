package com.example.finalproject.LukaGenerated.Pojos.IngredientPojo;


import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("idIngredient")
    private String idIngredient;

    @SerializedName("strIngredient")
    private String ingredientName;

    @SerializedName("strDescription")
    private String description;

    @SerializedName("strType")
    private String type;

    // Constructor
    public Ingredient(String idIngredient, String ingredientName, String description, String type) {
        this.idIngredient = idIngredient;
        this.ingredientName = ingredientName;
        this.description = description;
        this.type = type;
    }

    // Getters
    public String getIdIngredient() { return idIngredient; }
    public String getIngredientName() { return ingredientName; }
    public String getDescription() { return description; }
    public String getType() { return type; }

    // Setters
    public void setIdIngredient(String idIngredient) { this.idIngredient = idIngredient; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
}
