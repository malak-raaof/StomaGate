package com.example.finalproject.LukaGenerated.HomeStuff;

import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.RandomMeal;

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
            .subscribe(meallist -> view.showData(meallist));
    }

    public void addToFav(RandomMeal meal) { repo.insertMealRepo(meal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe();
    }

}