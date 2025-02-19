package com.example.finalproject.LukaGenerated.SearchStuff.Presenters;

import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.Area;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.Category;
import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.Ingredient;
import com.example.finalproject.LukaGenerated.SearchStuff.Interfaces.SearchInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {


    private SearchInterface view;

    private MealRepository repo;

    List<Category> backupCategoryList = new ArrayList<>();
    List<Ingredient> backupIngredientList = new ArrayList<>();
    List<Area> backupAreaList = new ArrayList<>();

    public SearchPresenter(SearchInterface view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getCategory(){repo.getCategoryRepo()
            .subscribeOn(Schedulers.io())
            .map(item -> item.getCategories())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(
                    categorieslist -> {
                        backupCategoryList= categorieslist;
                        view.showCategoryList(categorieslist);});
    }

    public void getIngredient(){repo.getIngredientRepo()
            .subscribeOn(Schedulers.io())
            .map(item -> item.getIngredients())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(
                    ingredientslist -> {
                        backupIngredientList= ingredientslist;
                        view.showIngredientsList(ingredientslist);});
    }

    public void getArea(){repo.getAreaRepo()
            .subscribeOn(Schedulers.io())
            .map(item -> item.getAreas())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(
                    areaslist -> {
                        backupAreaList= areaslist;
                        view.showAreaList(areaslist);});
    }


    public void filterCategoryList(String text) {
        if (text == null || text.trim().isEmpty()) {
            view.showCategoryList(backupCategoryList);
        } else {
            List<Category> filteredList = backupCategoryList.stream().filter(
                    category -> category.getCategoryName().toLowerCase().startsWith(text.toLowerCase().trim())
            ).collect(Collectors.toList());
            view.showCategoryList(filteredList);
        }
    }

    public void filterIngredientList(String text) {
        if (text == null || text.trim().isEmpty()) {
            view.showIngredientsList(backupIngredientList);
        } else {
            List<Ingredient> filteredList = backupIngredientList.stream().filter(
                    ingredient -> ingredient.getIngredientName().toLowerCase().startsWith(text.toLowerCase().trim())
            ).collect(Collectors.toList());
            view.showIngredientsList(filteredList);
        }
    }

    public void filterAreaList(String text) {
        if (text == null || text.trim().isEmpty()) {
            view.showAreaList(backupAreaList);
        } else {
            List<Area> filteredList = backupAreaList.stream().filter(
                    area -> area.getAreaName().toLowerCase().startsWith(text.toLowerCase().trim())
            ).collect(Collectors.toList());
            view.showAreaList(filteredList);
        }
    }


    public void searchBySection(String sectionName, String text) {
        if(sectionName!=null && text!=null) {
            switch (sectionName) {
                case "Category":
                    filterCategoryList(text);
                    break;
                case "Ingredient":
                    filterIngredientList(text);
                    break;
                case "Area":
                    filterAreaList(text);
                    break;
            }
        }
    }



}
