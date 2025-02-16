package com.example.finalproject.LukaGenerated.InfoStuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;
import java.util.ArrayList;
import java.util.List;

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.MyViewHolder> {

    private Context context;
    private List<String> ingredients;
    private List<String> measures;

    public FoodInfoAdapter(Context context, RandomMeal randomMeal) {
        this.context = context;
        this.ingredients = randomMeal.getIngredients();
        this.measures = randomMeal.getMeasures();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_info, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get ingredient and measure at current position
        String ingredient = ingredients.get(position);
        String measure = measures.get(position);

        holder.txtIngridient.setText(ingredient);
        holder.txtMeasure.setText(measure);

        // Load ingredient image
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + "-Small.png";
        Glide.with(context)
                .load(imageUrl)
                //.placeholder(R.drawable.placeholder_image)  // Optional: Add a placeholder
                //.error(R.drawable.error_image)             // Optional: Handle error cases
                .into(holder.imageViewIngridient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();  // Return the number of ingredients
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIngridient;
        TextView txtIngridient;
        TextView txtMeasure;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIngridient = itemView.findViewById(R.id.txtIngridient);
            txtMeasure = itemView.findViewById(R.id.txtMeasure);
            imageViewIngridient = itemView.findViewById(R.id.imageViewIngridient);
        }
    }
}
