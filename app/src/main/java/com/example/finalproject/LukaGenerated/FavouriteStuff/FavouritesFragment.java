package com.example.finalproject.LukaGenerated.FavouriteStuff;

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

public class FavouritesFragment extends Fragment implements OnRemFavClickListener, FavouritesInterface {


    RecyclerView favReckView;

    FavouritesAdapter favAdapter;

    RecyclerView.LayoutManager layoutManager;

    FavouritesPresenter favPresenter;

    public static final String TAG = "FavouritesFrag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);


        favReckView = view.findViewById(R.id.favReckView);
        favAdapter = new FavouritesAdapter(getContext(), this);
        layoutManager = new LinearLayoutManager(getContext());
        favReckView.setLayoutManager(layoutManager);
        favReckView.setAdapter(favAdapter);

        favPresenter = new FavouritesPresenter(this,
                MealRepository.getInstance(RemoteDataSource.getInstance(),
                        LocalDataSource.getInstance(getContext())));


        favPresenter.getFavMeals();

        return view;
    }

    @Override
    public void showFavData(List<RandomMeal> meals) {

        favAdapter.setList((ArrayList<RandomMeal>) meals);
        favAdapter.notifyDataSetChanged();;

    }

    @Override
    public void onRemFavMealClick(RandomMeal meal) {

        Toast.makeText(getContext(),"Product Removed From Favourites",Toast.LENGTH_SHORT).show();
        favPresenter.removeFromFav(meal);

    }
}