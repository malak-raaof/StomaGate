package com.example.finalproject.LukaGenerated.Generic.Network;

import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("random.php")
    Single<RandomMealResponse> getRandomMealService();

    @GET("lookup.php")
    Single<RandomMealResponse> getMealByIdService(@Query("i") String mealId);


}
