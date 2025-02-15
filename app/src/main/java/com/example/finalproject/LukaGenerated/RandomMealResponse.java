package com.example.finalproject.LukaGenerated;
import com.example.finalproject.LukaGenerated.TrialPojo.Meal;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RandomMealResponse {

    @SerializedName("meals")
    private List<RandomMeal> meals;

    public RandomMealResponse(List<RandomMeal> meals) {
        this.meals = meals;
    }

        public List<RandomMeal> getMeals() {
            return meals;
        }

        public void setMeals(List<RandomMeal> meals) {
            this.meals = meals;
        }
    }


