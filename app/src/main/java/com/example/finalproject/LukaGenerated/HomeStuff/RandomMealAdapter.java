package com.example.finalproject.LukaGenerated.HomeStuff;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;

import java.util.ArrayList;

public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealAdapter.MyViewHolder> {

    Context context;

    private ArrayList<RandomMeal> randomMeals;

    public static final String TAG = "RandomMealAdapter";

    public RandomMealAdapter(Context context, ArrayList<RandomMeal> randomMeals) {
        this.context = context;
        this.randomMeals = randomMeals;
    }


    @NonNull
    @Override
    public RandomMealAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_item_home,parent,false);
        RandomMealAdapter.MyViewHolder myViewHolder = new RandomMealAdapter.MyViewHolder(view);
        Log.i(TAG,"=============OnCreateViewHolder=============");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RandomMeal current = randomMeals.get(position);
        holder.textviewCardHome.setText(current.getStrMeal());
        Glide.with(context).load(current.getStrMealThumb())
                .into(holder.imgviewCardHome);

        Log.i(TAG,"=======onBindViewHolder=========");

        holder.constriantHomeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragmentDirections.ActionHomeFragmentToFoodInfoFragment action =
                        HomeFragmentDirections.actionHomeFragmentToFoodInfoFragment(current);
                Navigation.findNavController(view).navigate(action);

            }
        });

    }

    @Override
    public int getItemCount() {
        return randomMeals.size();
    }

    public void updateData(ArrayList<RandomMeal> updatedMeals) {

        this.randomMeals = updatedMeals;

    }

    public void clear(){ this.randomMeals.clear(); }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgviewCardHome;
        TextView textviewCardHome;
        ConstraintLayout constriantHomeCard;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgviewCardHome = itemView.findViewById(R.id.imgviewCardHome);
            textviewCardHome = itemView.findViewById(R.id.textviewCardHome);
            constriantHomeCard = itemView.findViewById(R.id.constriantHomeCard);
        }
    }
}
