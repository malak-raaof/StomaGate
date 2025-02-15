package com.example.finalproject.LukaGenerated;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RandomMeal implements Serializable {

        private String idMeal;
        private String strMeal;
        private String strCategory;
        private String strArea;
        private String strInstructions;
        private String strMealThumb;
        private String strTags;
        private String strYoutube;
        private String strSource;
        private List<String> ingredients;
        private List<String> measures;



        // ✅ Empty Constructor (Required for Gson)
        public RandomMeal() {}


        // ✅ Getters & Setters
        public String getIdMeal() { return idMeal; }
        public void setIdMeal(String idMeal) { this.idMeal = idMeal; }

        public String getStrMeal() { return strMeal; }
        public void setStrMeal(String strMeal) { this.strMeal = strMeal; }

        public String getStrCategory() { return strCategory; }
        public void setStrCategory(String strCategory) { this.strCategory = strCategory; }

        public String getStrArea() { return strArea; }
        public void setStrArea(String strArea) { this.strArea = strArea; }

        public String getStrInstructions() { return strInstructions; }
        public void setStrInstructions(String strInstructions) { this.strInstructions = strInstructions; }

        public String getStrMealThumb() { return strMealThumb; }
        public void setStrMealThumb(String strMealThumb) { this.strMealThumb = strMealThumb; }

        public String getStrTags() { return strTags; }
        public void setStrTags(String strTags) { this.strTags = strTags; }

        public String getStrYoutube() { return strYoutube; }
        public void setStrYoutube(String strYoutube) { this.strYoutube = strYoutube; }

        public String getStrSource() { return strSource; }
        public void setStrSource(String strSource) { this.strSource = strSource; }

        public List<String> getIngredients() { return ingredients; }
        public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }

        public List<String> getMeasures() { return measures; }
        public void setMeasures(List<String> measures) { this.measures = measures; }





    public void extractIngredientsAndMeasures() {
        ingredients = new ArrayList<>();
        measures = new ArrayList<>();

        try {
            for (int i = 1; i <= 20; i++) {
                Field ingredientField = this.getClass().getDeclaredField("strIngredient" + i);
                Field measureField = this.getClass().getDeclaredField("strMeasure" + i);

                ingredientField.setAccessible(true);
                measureField.setAccessible(true);

                String ingredient = (String) ingredientField.get(this);
                String measure = (String) measureField.get(this);

                if (ingredient != null && !ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                    measures.add(measure);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}





