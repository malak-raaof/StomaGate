package com.example.finalproject.LukaGenerated.SearchStuff.Interfaces;

import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.Area;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.Category;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.Ingredient;

import java.util.List;

public interface SearchInterface {

    public void showCategoryList(List<Category> categories);

    public void showIngredientsList(List<Ingredient> ingredients);
    public void showAreaList(List<Area> areas);

}
