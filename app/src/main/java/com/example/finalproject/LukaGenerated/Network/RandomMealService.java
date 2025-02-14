package com.example.finalproject.LukaGenerated.Network;

import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RandomMealService {

    @GET("meals")
    Single<RandomMealResponse> getProducts();

}
