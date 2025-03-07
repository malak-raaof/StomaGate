package com.example.finalproject.LukaGenerated.Generic.Network;


import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.AreaResponse;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.CategoryResponse;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.IngredientResponse;
import com.example.finalproject.LukaGenerated.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//Networking Code
public class RemoteDataSource {


    public static final String TAG = "MealClient";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static RemoteDataSource client = null;

    private Service mealService;



    private RemoteDataSource(){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();


        mealService = retrofit.create(Service.class);

    }


    public static RemoteDataSource getInstance(){
        if(client == null){
            client = new RemoteDataSource();
        }
        return client;
    }

    public Single<RandomMealResponse> makeRandomNetworkCall() {
        Single<RandomMealResponse> myCall = mealService.getRandomMealService();
        return myCall;
    }

    public Single<RandomMealResponse> makeMealIdNetworkCall(String mealId) {
        Single<RandomMealResponse> myCall = mealService.getMealByIdService(mealId);
        return myCall;
    }

    public Single<CategoryResponse> makeCategoryNetworkCall() {
        Single<CategoryResponse> myCall = mealService.getCategoriesService();
        return myCall;
    }

    public Single<IngredientResponse> makeIngredientNetworkCall() {
        Single<IngredientResponse> myCall = mealService.getIngredientsService();
        return myCall;
    }

    public Single<AreaResponse> makeAreaNetworkCall() {
        Single<AreaResponse> myCall = mealService.getAreasService();
        return myCall;
    }


    public Single<RandomMealResponse> makeMealCategoryNetworkCall(String category) {
        Single<RandomMealResponse> myCall = mealService.getMealsByCategoryService(category);
        return myCall;
    }

    public Single<RandomMealResponse> makeMealIngredientNetworkCall(String ingredient) {
        Single<RandomMealResponse> myCall = mealService.getMealsByIngredientService(ingredient);
        return myCall;
    }

    public Single<RandomMealResponse> makeMealAreaNetworkCall(String area) {
        Single<RandomMealResponse> myCall = mealService.getMealsByAreaService(area);
        return myCall;
    }

}
