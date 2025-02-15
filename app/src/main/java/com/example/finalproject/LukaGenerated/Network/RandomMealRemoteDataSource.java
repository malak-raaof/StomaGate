package com.example.finalproject.LukaGenerated.Network;


import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//Networking Code
public class RandomMealRemoteDataSource {


    public static final String TAG = "MealClient";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static RandomMealRemoteDataSource client = null;

    private RandomMealService randomMealService;



    private RandomMealRemoteDataSource(){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();


        randomMealService = retrofit.create(RandomMealService.class);

    }


    public static RandomMealRemoteDataSource getInstance(){
        if(client == null){
            client = new RandomMealRemoteDataSource();
        }
        return client;
    }

    public Single<RandomMealResponse> makeNetworkCall() {
        Single<RandomMealResponse> myCall = randomMealService.getMeals();
        return myCall;
    }

}
