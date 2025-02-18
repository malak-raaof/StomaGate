package com.example.finalproject.LukaGenerated.Generic.Network;

import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.AreaResponse;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.CategoryResponse;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.IngredientResponse;
import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("random.php")
    Single<RandomMealResponse> getRandomMealService();

    @GET("lookup.php")
    Single<RandomMealResponse> getMealByIdService(@Query("i") String mealId);

    @GET("categories.php")
    Single<CategoryResponse> getCategoriesService();

    @GET("list.php?i=list")
    Single<IngredientResponse> getIngredientsService();

    @GET("list.php?a=list")
    Single<AreaResponse> getAreasService();

    @GET("filter.php")
    Single<RandomMealResponse> getMealsByCategoryService(@Query("c") String categoryName);

    @GET("filter.php")
    Single<RandomMealResponse> getMealsByIngredientService(@Query("i") String ingredientName);

    @GET("filter.php")
    Single<RandomMealResponse> getMealsByAreaService(@Query("a") String areaName);


}
