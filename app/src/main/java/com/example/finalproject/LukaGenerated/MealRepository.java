package com.example.finalproject.LukaGenerated;

import com.example.finalproject.LukaGenerated.Network.RandomMealRemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class MealRepository {

    RandomMealRemoteDataSource randomMealRemoteDataSource;

    private static MealRepository repo = null;

    public MealRepository(RandomMealRemoteDataSource randomMealRemoteDataSource) {
        this.randomMealRemoteDataSource = randomMealRemoteDataSource;
    }

    public static MealRepository getInstance(RandomMealRemoteDataSource randomMealRemoteDataSource){

        if(repo==null){
            repo = new MealRepository(randomMealRemoteDataSource);
        }
        return repo;
    }


    public Single<RandomMealResponse> getRandomMeal(){ return randomMealRemoteDataSource.makeNetworkCall(); }


}
