package com.example.finalproject.LukaGenerated.Generic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.finalproject.LukaGenerated.RandomMeal;

@Database(entities = {RandomMeal.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class MealsDataBase extends RoomDatabase {

    private static MealsDataBase instance = null;
    public abstract MealDao getMealDao();
    public static MealsDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, MealsDataBase.class, "MealsDB").build();
        }
        return instance;
    }

}
