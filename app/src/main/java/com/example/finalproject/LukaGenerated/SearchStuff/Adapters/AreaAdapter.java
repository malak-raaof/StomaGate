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
import com.example.finalproject.LukaGenerated.CountryImage;
import com.example.finalproject.LukaGenerated.Pojos.AreaPojo.Area;
import com.example.finalproject.LukaGenerated.SearchStuff.OnSearchClickListener;
import com.example.finalproject.R;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder> {

    public static final String TAG = "AreaAdapter";
    private Context context;
    private List<Area> areas;
    private OnSearchClickListener listener;

    public AreaAdapter(Context context, List<Area> areas, OnSearchClickListener listener) {
        this.context = context;
        this.areas = areas;
        this.listener = listener;
    }

    public void setList(List<Area> areas){
        this.areas=areas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item_home, parent, false);
        AreaAdapter.MyViewHolder myViewHolder = new AreaAdapter.MyViewHolder(view);
        Log.i(TAG, "==========ON CREATE VIEW HOLDER");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Area current = areas.get(position);

        Glide.with(context)
                .load(CountryImage.getFlagUrl(current.getAreaName()))
                .into(holder.imgviewCardSearch);

        holder.textviewCardSearch.setText(current.getAreaName());
        holder.constriantSearchCard.setOnClickListener(v -> {
            listener.OnSearchClick("Area", current.getAreaName());

        });

    }




    @Override
    public int getItemCount() {
        return areas.size();
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