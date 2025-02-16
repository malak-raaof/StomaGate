package com.example.finalproject.LukaGenerated.HomeStuff;

import com.example.finalproject.LukaGenerated.Generic.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomMealPresenter {

    private RandomMealInterface view;

    private MealRepository repo;

    public RandomMealPresenter(RandomMealInterface view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getMeal(){repo.getRandomMealRepo()
            .subscribeOn(Schedulers.io())
            .map(item -> item.getMeals())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(meallist -> view.showData(meallist));}

}