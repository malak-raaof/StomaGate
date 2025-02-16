package com.example.finalproject.LukaGenerated.Generic;

import com.example.finalproject.LukaGenerated.Generic.Network.RemoteDataSource;
import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;

public class MealRepository {

    RemoteDataSource remoteDataSource;

    private static MealRepository repo = null;

    public MealRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MealRepository getInstance(RemoteDataSource remoteDataSource){

        if(repo==null){
            repo = new MealRepository(remoteDataSource);
        }
        return repo;
    }


    public Single<RandomMealResponse> getRandomMealRepo(){ return remoteDataSource.makeRandomNetworkCall(); }

    public Single<RandomMealResponse> getMealByIdRepo(String mealId) { return remoteDataSource.makeMealIdNetworkCall(mealId); }


}
