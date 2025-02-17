package com.example.finalproject.LukaGenerated.Generic;

import android.content.Context;

import com.example.finalproject.LukaGenerated.RandomMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class LocalDataSource {

    private Context context;

    private MealDao mealDao;

    private Observable<List<RandomMeal>> storedMeals;

    private static LocalDataSource localDataSource = null;


    private LocalDataSource(Context context){

        this.context = context;
        MealsDataBase db = MealsDataBase.getInstance(context.getApplicationContext());
        mealDao=db.getMealDao();
        storedMeals = mealDao.getAllMealsDao();
    }


    public static LocalDataSource getInstance(Context context){
        if(localDataSource == null){
            localDataSource = new LocalDataSource(context);
        }
        return localDataSource;
    }


    public Observable<List<RandomMeal>> getStoredMealsLocal(){ return mealDao.getAllMealsDao(); }


    public Completable deleteLocal(RandomMeal meal){
        return mealDao.deleteDao(meal);
    }


    public Completable insertLocal(RandomMeal meal){
        return mealDao.insertDao(meal);
    }


}
