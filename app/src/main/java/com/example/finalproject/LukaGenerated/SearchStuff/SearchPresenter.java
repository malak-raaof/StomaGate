package com.example.finalproject.LukaGenerated.SearchStuff;

import com.example.finalproject.LukaGenerated.Generic.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {


    private SearchInterface view;

    private MealRepository repo;

    public SearchPresenter(SearchInterface view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getCategory(){repo.getCategotyRepo()
            .subscribeOn(Schedulers.io())
            .map(item -> item.getCategories())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(categorieslist -> view.showData(categorieslist)); }
}
