package com.example.finalproject.LukaGenerated.FavouriteStuff;

import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.RandomMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritesPresenter {

    private FavouritesInterface view;
    private MealRepository repo;

    public FavouritesPresenter(FavouritesInterface view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getFavMeals(){
        repo.getStoredMealsRepo()
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribe(meallist -> view.showFavData( meallist));
    }


    public void removeFromFav(RandomMeal meal){
        repo.deleteMealRepo(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
