package com.example.finalproject.LukaGenerated.Generic;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalproject.LukaGenerated.RandomMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertDao(RandomMeal meal);

    @Delete
    Completable deleteDao(RandomMeal product);

    @Query("Select * from my_meal_table")
    Observable<List<RandomMeal>> getAllMealsDao();


}
