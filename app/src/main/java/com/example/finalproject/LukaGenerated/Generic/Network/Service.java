package com.example.finalproject.LukaGenerated.Generic.Network;

import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface Service {

    @GET("random.php")
    Single<RandomMealResponse> getRandomMealService();

}
