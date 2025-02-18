package com.example.finalproject.LukaGenerated.SearchStuff.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.Category;
import com.example.finalproject.LukaGenerated.SearchStuff.OnSearchClickListener;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{


    public static final String TAG = "CategoryAdapter";

    private Context context;

    private List<Category> categories;

    private OnSearchClickListener listener;

    public CategoryAdapter(Context context,List<Category> categories, OnSearchClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    public void setList(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item_home,parent,false);
        CategoryAdapter.MyViewHolder myViewHolder = new CategoryAdapter.MyViewHolder(view);
        Log.i(TAG, "==========ON CREATE VIEW HOLDER");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {

        Category current = categories.get(position);

        holder.textviewCardSearch.setText(current.getCategoryName());
        Glide.with(context).load(current.getCategoryThumb())
                .into(holder.imgviewCardSearch);

        Log.i(TAG,"=======onBindViewHolder=========");

        holder.constriantSearchCard.setOnClickListener(v -> {
            listener.OnSearchClick("Category",current.getCategoryName());

        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

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
