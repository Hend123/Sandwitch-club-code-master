package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)  {
        List<String> mAlsoKnownAsList  = new ArrayList<>();
        List<String> mIngredientList  = new ArrayList<>();
        Sandwich sandwich = null;
        try {
            JSONObject mainObject = new JSONObject(json);
          JSONObject nameObject =   mainObject.getJSONObject("name");
          String mainName = nameObject.getString("mainName");
           JSONArray alsoKnownAsArray  =  nameObject.getJSONArray("alsoKnownAs");
           for (int i = 0;i<alsoKnownAsArray .length();i++){
              String alsoKnownAs  =  alsoKnownAsArray .getString(i);
               mAlsoKnownAsList .add(alsoKnownAs );
           }
           String placeOfOrigin  = mainObject.getString("placeOfOrigin");
            String description  =   mainObject.getString("description");
            String image  =   mainObject.getString("image");
            JSONArray ingredientsArray  =  mainObject.getJSONArray("ingredients");
            for (int i = 0; i< ingredientsArray .length();i++){
                String ingredient  =  ingredientsArray .getString(i);
                mIngredientList .add(ingredient );
            }
             sandwich = new Sandwich(mainName,mAlsoKnownAsList ,placeOfOrigin ,description ,image , mIngredientList );
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sandwich;
    }
}
