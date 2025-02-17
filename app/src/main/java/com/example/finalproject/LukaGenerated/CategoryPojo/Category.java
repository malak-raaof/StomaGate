package com.example.finalproject.LukaGenerated.CategoryPojo;


import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("idCategory")
    private String idCategory;

    @SerializedName("strCategory")
    private String categoryName;

    @SerializedName("strCategoryThumb")
    private String categoryThumb;

    @SerializedName("strCategoryDescription")
    private String categoryDescription;

    // Constructor
    public Category(String idCategory, String categoryName, String categoryThumb, String categoryDescription) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryThumb = categoryThumb;
        this.categoryDescription = categoryDescription;
    }

    // Getters
    public String getIdCategory() { return idCategory; }
    public String getCategoryName() { return categoryName; }
    public String getCategoryThumb() { return categoryThumb; }
    public String getCategoryDescription() { return categoryDescription; }

    // Setters
    public void setIdCategory(String idCategory) { this.idCategory = idCategory; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCategoryThumb(String categoryThumb) { this.categoryThumb = categoryThumb; }
    public void setCategoryDescription(String categoryDescription) { this.categoryDescription = categoryDescription; }
}

