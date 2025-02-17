package com.example.finalproject.LukaGenerated.HomeStuff;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.LukaGenerated.Generic.LocalDataSource;
import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.Generic.Network.RemoteDataSource;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RandomMealInterface {


    RecyclerView reckviewHome;
    RandomMealAdapter randomMealAdapter;
    RandomMealPresenter randomMealPresenter;
    LinearLayoutManager layoutManager;

    public static final String TAG = "HomeFrag";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_me, container, false);
        reckviewHome = view.findViewById(R.id.reckviewHome);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        randomMealPresenter = new RandomMealPresenter(this,
                MealRepository.getInstance(RemoteDataSource.getInstance(),
                        LocalDataSource.getInstance(getContext())));

        randomMealAdapter = new RandomMealAdapter(getContext(),new ArrayList<>());
        reckviewHome.setAdapter(randomMealAdapter);
        reckviewHome.setLayoutManager(layoutManager);



        randomMealPresenter.getMeal();

        return view;
    }


    @Override
    public void showData(List<RandomMeal> randomMeals) {

        randomMealAdapter.updateData((ArrayList<RandomMeal>) randomMeals);
        randomMealAdapter.notifyDataSetChanged();

    }
}