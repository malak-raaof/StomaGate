package com.example.finalproject.LukaGenerated.FavouriteStuff;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>{


    public static final String TAG = "FavouriteAdapter";

    private Context context;

    private ArrayList<RandomMeal> meals;

    private OnRemFavClickListener listener;


    public FavouritesAdapter(Context context, OnRemFavClickListener listener) {
        this.context = context;
        this.listener=listener;
        meals = new ArrayList<RandomMeal>();
    }


    public void setList(ArrayList<RandomMeal> updatedMeals) {

        this.meals = updatedMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavouritesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item_fav,parent,false);
        FavouritesAdapter.MyViewHolder myViewHolder = new FavouritesAdapter.MyViewHolder(view);
        Log.i(TAG, "==========ON CREATE VIEW HOLDER");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MyViewHolder holder, int position) {

        RandomMeal current = meals.get(position);

        holder.textviewCardFav.setText(current.getStrMeal());
        Glide.with(context).load(current.getStrMealThumb())
                .into(holder.imgviewCardFav);

        Log.i(TAG,"=======onBindViewHolder=========");


        holder.imgbtnFavCardHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onRemFavMealClick(current);

            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgviewCardFav;
        TextView textviewCardFav;
        ConstraintLayout constriantFavCard;
        ImageButton imgbtnFavCardHeart;

        float noOfStars;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewCardFav = itemView.findViewById(R.id.imgviewCardFav);
            textviewCardFav = itemView.findViewById(R.id.textviewCardFav);
            constriantFavCard = itemView.findViewById(R.id.constriantFavCard);
            imgbtnFavCardHeart = itemView.findViewById(R.id.imgbtnFavCardHeart);
        }
    }
}
