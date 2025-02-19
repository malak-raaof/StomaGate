package com.example.finalproject.LukaGenerated.SearchStuff.Presenters;

import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.LukaGenerated.SearchStuff.SearchObject;
import com.example.finalproject.LukaGenerated.SearchStuff.Interfaces.SearchSectionInterface;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchSectionPresenter {
    MealRepository repo;
    SearchSectionInterface view;
    List<RandomMeal> mealsBackup = new ArrayList<>();


    public SearchSectionPresenter(MealRepository mealsRepository, SearchSectionInterface view) {
        this.repo = mealsRepository;
        this.view = view;
    }

    public void getAllMeals(SearchObject searchItem) {
        switch (searchItem.getSectionName()) {
            case "Category":
                getMealsByCategory(searchItem.getItemName());
                break;
            case "Area":
                getMealsByArea(searchItem.getItemName());
                break;
            case "Ingredient":
                getMealsByIngredient(searchItem.getItemName());
                break;
        }


    }


    public void getMealsByCategory(String categoryName) {
        repo.getMealByCategoryRepo(categoryName)
                .subscribeOn(Schedulers.io())
                .map(mealResponse -> mealResponse.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            mealsBackup = list;
                            view.showMeals(list);});
    }

    public void getMealsByArea(String areaName) {
        repo.getMealByAreaRepo(areaName)
                .subscribeOn(Schedulers.io())
                .map(mealResponse -> mealResponse.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list ->  {
                            mealsBackup = list;
                            view.showMeals(list);});
    }

    public void getMealsByIngredient(String ingredientName) {
        repo.getMealByIngredientRepo(ingredientName)
                .subscribeOn(Schedulers.io())
                .map(mealResponse -> mealResponse.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list ->  {
                            mealsBackup = list;
                            view.showMeals(list);});
    }

    public void searchByMeal(String text) {
        if (text == null || text.trim().isEmpty()) {
            view.showMeals(mealsBackup);
        } else {
            List<RandomMeal> filteredList = mealsBackup.stream().filter(
                    meal -> meal.getStrMeal().toLowerCase().startsWith(text.toLowerCase().trim())
            ).collect(Collectors.toList());
            view.showMeals(filteredList);
        }
    }


}