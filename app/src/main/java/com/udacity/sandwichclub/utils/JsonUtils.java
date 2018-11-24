package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        JSONObject mainObject =null;
        try {
             mainObject = new JSONObject(json);
             JSONObject name = mainObject.getJSONObject("name");
             String mainName = name.getString("mainName");
             JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
             String placeOfOrigin = mainObject.getString("placeOfOrigin");
             String description = mainObject.getString("description");
             String image = mainObject.getString("image");
             JSONArray ingredients = mainObject.getJSONArray("ingredients");


             // convert alsoKnownAs fro JSONArray to ArrayList of String
            ArrayList<String> alsoKnownAsString = new ArrayList<>();
            for(int i = 0 ; i< alsoKnownAs.length() ; i++){
                alsoKnownAsString.add(alsoKnownAs.getString(i));
            }

            // convert ingrediant for JSONArray to ArrayList of String
            ArrayList<String> ingredientsString = new ArrayList<>();
            for(int i = 0 ; i< ingredients.length() ; i++){
                ingredientsString.add(ingredients.getString(i));
            }

            // put values in sandwich object
            sandwich.setMainName(mainName);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredientsString);
            sandwich.setAlsoKnownAs(alsoKnownAsString);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
