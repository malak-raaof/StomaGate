package com.example.finalproject.LukaGenerated.SearchStuff.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalproject.LukaGenerated.Pojos.IngredientPojo.Ingredient;
import com.example.finalproject.LukaGenerated.SearchStuff.OnSearchClickListener;
import com.example.finalproject.R;


import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder>{

    public static final String TAG = "IngredientAdapter";
    private Context context;
    private List<Ingredient> ingredients;
    private OnSearchClickListener listener;


    public IngredientAdapter(Context context, List<Ingredient> ingredients, OnSearchClickListener listener) {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener;
    }

    public void setList(List<Ingredient> ingredients){
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item_home, parent, false);
        IngredientAdapter.MyViewHolder myViewHolder = new IngredientAdapter.MyViewHolder(view);
        Log.i(TAG, "==========ON CREATE VIEW HOLDER");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredient current = ingredients.get(position);

        holder.textviewCardSearch.setText(current.getIngredientName());
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/" + current.getIngredientName() + ".png")
                .into(holder.imgviewCardSearch);

        Log.i(TAG,"=======onBindViewHolder=========");

        holder.constriantSearchCard.setOnClickListener(v -> {
            listener.OnSearchClick("Ingredient", current.getIngredientName());

        });

    }



    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgviewCardSearch;
        TextView textviewCardSearch;
        ConstraintLayout constriantSearchCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewCardSearch = itemView.findViewById(R.id.imgviewCardHome);
            textviewCardSearch = itemView.findViewById(R.id.textviewCardHome);
            constriantSearchCard = itemView.findViewById(R.id.constriantHomeCard);
        }
    }
}