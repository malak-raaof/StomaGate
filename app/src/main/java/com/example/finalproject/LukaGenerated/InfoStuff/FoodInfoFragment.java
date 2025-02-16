package com.example.finalproject.LukaGenerated.InfoStuff;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.R;
import com.google.gson.Gson;

import java.util.List;

public class FoodInfoFragment extends Fragment {

    ImageButton imgbtnCardHeart;
    ImageView imgviewMealInfo;
    TextView textviewMealName, txtOrigin, txtSteps;
    WebView webviewInfo;
    RecyclerView reckviewIngredients;
    LinearLayoutManager layoutManager;
    FoodInfoAdapter foodInfoAdapter;

    public static final String TAG = "FoodInfoFrag";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_info, container, false);

        RandomMeal randomMeal = FoodInfoFragmentArgs.fromBundle(getArguments()).getRandomMeal();
        randomMeal.extractIngredientsAndMeasures();

        reckviewIngredients = view.findViewById(R.id.reckviewIngredients);
        imgviewMealInfo = view.findViewById(R.id.imgviewMealInfo);
        textviewMealName = view.findViewById(R.id.textviewMealName);
        txtOrigin = view.findViewById(R.id.txtOrigin);
        txtSteps = view.findViewById(R.id.txtSteps);
        webviewInfo = view.findViewById(R.id.webviewInfo);
        imgbtnCardHeart = view.findViewById(R.id.imgbtnCardHeart);


        // Get the video URL from the meal object
        String videoUrl = randomMeal.getStrYoutube();

        if (videoUrl != null && !videoUrl.isEmpty()) {
            videoUrl = videoUrl.replace("watch?v=", "embed/") + "?autoplay=0"; // Ensure it's an embedded URL & no autoplay

            // Configure WebView settings
            webviewInfo.getSettings().setJavaScriptEnabled(true);
            webviewInfo.getSettings().setMediaPlaybackRequiresUserGesture(true); // Prevents autoplay

            // Load the video
            webviewInfo.loadUrl(videoUrl);
        }



        Glide.with(this)
                .load(randomMeal.getStrMealThumb())
                //.placeholder(R.drawable.placeholder_image) // Optional: Add a placeholder
                //.error(R.drawable.error_image) // Optional: Error image if loading fails
                .into(imgviewMealInfo);
        textviewMealName.setText(randomMeal.getStrMeal());
        txtOrigin.setText(randomMeal.getStrArea());
        txtSteps.setText(randomMeal.getStrInstructions());
        //webviewInfo.loadUrl(randomMeal.getStrYoutube());

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //List<String> ingredients = randomMeal.getIngredients(); // Get the ingredient list
        //List<String> measures = randomMeal.getMeasures();       // Get the measure list

        randomMeal.extractIngredientsAndMeasures();

        Log.d(TAG, "Full API Response: " + new Gson().toJson(randomMeal));
        Log.d(TAG, "Ingredients: " + randomMeal.getIngredients());
        Log.d(TAG, "Measures: " + randomMeal.getMeasures());


        foodInfoAdapter = new FoodInfoAdapter(getContext(), randomMeal);
        reckviewIngredients.setAdapter(foodInfoAdapter);
        reckviewIngredients.setLayoutManager(layoutManager);




        imgbtnCardHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgbtnCardHeart.isSelected()) {
                    imgbtnCardHeart.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                } else {
                    imgbtnCardHeart.setColorFilter(ContextCompat.getColor(getContext(), R.color.Dark_Red));
                }
                imgbtnCardHeart.setSelected(!imgbtnCardHeart.isSelected());
            }
        });

        return view;

    }
}