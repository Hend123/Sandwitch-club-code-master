package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private List<String> mAlsoKnowsAsList;
    private List<String> mIngredientsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mSandwichIv = findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .into(mSandwichIv);


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView mPlaceOriginTv = findViewById(R.id.origin_tv);
        TextView mAlsoKnowsTv = findViewById(R.id.also_known_tv);
        TextView mDescriptionTv = findViewById(R.id.description_tv);
        TextView mIngredientsTv = findViewById(R.id.ingredients_tv);

        mDescriptionTv.setText(check(sandwich.getDescription()));
        mPlaceOriginTv.setText(check(sandwich.getPlaceOfOrigin()));
        mAlsoKnowsAsList = new ArrayList<>();
        mIngredientsList = new ArrayList<>();

        mAlsoKnowsAsList = sandwich.getAlsoKnownAs();


        String alsoKnowAs = "";

        for (int i = 0; i < mAlsoKnowsAsList.size(); i++) {
            alsoKnowAs += String.format("* %s", mAlsoKnowsAsList.get(i)+ "." + "\n ");
            }

        mAlsoKnowsTv.setText(check(alsoKnowAs));
        mIngredientsList = sandwich.getIngredients();

        String ingredient = "";
        for (int i = 0; i < mIngredientsList.size(); i++) {
            ingredient += String.format("* %s",mIngredientsList.get(i)+"." + "\n ");

        }
        mIngredientsTv.setText(check(ingredient));

    }
    private String check(String string){
        if (string == null || string.equals("")){
            string = "Unknown";
        }
        return string;
    }
}
