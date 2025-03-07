package com.example.finalproject.LukaGenerated.Generic;

import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.AreaResponse;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.CategoryResponse;
import com.example.finalproject.LukaGenerated.Generic.Network.RemoteDataSource;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.IngredientResponse;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.LukaGenerated.RandomMealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepository {

    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;

    private static MealRepository repo = null;

    public MealRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static MealRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource){

        if(repo==null){
            repo = new MealRepository(remoteDataSource, localDataSource);
        }
        return repo;
    }

    public Observable<List<RandomMeal>> getStoredMealsRepo(){ return localDataSource.getStoredMealsLocal();}

    public Single<RandomMealResponse> getRandomMealRepo(){ return remoteDataSource.makeRandomNetworkCall(); }

    public Single<RandomMealResponse> getMealByIdRepo(String mealId) { return remoteDataSource.makeMealIdNetworkCall(mealId); }

    public Completable insertMealRepo(RandomMeal meal){ return localDataSource.insertLocal(meal);}

    public Completable deleteMealRepo(RandomMeal meal){ return localDataSource.deleteLocal(meal);}

    public Single<CategoryResponse> getCategoryRepo(){ return remoteDataSource.makeCategoryNetworkCall(); }

    public Single<IngredientResponse> getIngredientRepo(){ return remoteDataSource.makeIngredientNetworkCall(); }

    public Single<AreaResponse> getAreaRepo(){ return remoteDataSource.makeAreaNetworkCall(); }

    public Single<RandomMealResponse> getMealByCategoryRepo(String category){ return remoteDataSource.makeMealCategoryNetworkCall(category); }

    public Single<RandomMealResponse> getMealByIngredientRepo(String ingredient){ return remoteDataSource.makeMealIngredientNetworkCall(ingredient); }

    public Single<RandomMealResponse> getMealByAreaRepo(String area){ return remoteDataSource.makeMealAreaNetworkCall(area); }



}
