package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        //Sandwich Object to be returned
        Sandwich sandwich = null;
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            //Root Object
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject name = sandwichJson.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            List<String> alsoKnownAsNames = new ArrayList<>();

            for (int i = 0; i < alsoKnownAs.length(); i++) {
                String alsoKnownAsString = alsoKnownAs.getString(i);
                alsoKnownAsNames.add(alsoKnownAsString);
            }

            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String describtion = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");

            JSONArray ingredients = sandwichJson.getJSONArray("ingredients");
            List<String> ingredientsString = new ArrayList<>();
            // For each ingredient in the ingredients, create an {@link String} object
            for (int i = 0; i < ingredients.length(); i++) {
                // Get a single ingredient at position i within the list of ingredients
                String ingredientString = ingredients.getString(i);
                ingredientsString.add(ingredientString);
            }

            sandwich = new Sandwich(mainName, alsoKnownAsNames, placeOfOrigin, describtion, image, ingredientsString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
