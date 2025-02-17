package com.example.finalproject.LukaGenerated.SearchStuff;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.LukaGenerated.CategoryPojo.Category;
import com.example.finalproject.LukaGenerated.FavouriteStuff.FavouritesAdapter;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>{


    public static final String TAG = "FavouriteAdapter";

    private Context context;

    private ArrayList<Category> categories;

    @NonNull
    @Override
    public FavouritesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgviewCardSearch;
        TextView textviewCardSearch;
        ConstraintLayout constriantSearchCard;

        float noOfStars;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewCardSearch = itemView.findViewById(R.id.imgviewCardHome);
            textviewCardSearch = itemView.findViewById(R.id.textviewCardHome);
            constriantSearchCard = itemView.findViewById(R.id.constriantHomeCard);
        }
    }

}
