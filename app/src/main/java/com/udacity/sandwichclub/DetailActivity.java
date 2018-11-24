package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
        Log.v("true","true");
       setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        TextView alsoKnown = (TextView) findViewById(R.id.also_known_tv);
        TextView ingrediant = (TextView) findViewById(R.id.ingredients_tv);
        TextView origin = (TextView) findViewById(R.id.origin_tv);
        TextView descreption = (TextView) findViewById(R.id.description_tv);

        origin.setText(sandwich.getPlaceOfOrigin());
        descreption.setText(sandwich.getDescription());

        List<String> alsoKnownString = sandwich.getAlsoKnownAs();
        if(alsoKnownString != null) {
            for (int i = 0; i < alsoKnownString.size(); i++) {
                alsoKnown.append(alsoKnownString.get(i) + "\n");
            }
        }

        List<String> ingrediantString = sandwich.getIngredients();
        if(ingrediantString != null) {
            for (int i = 0; i < ingrediantString.size(); i++) {
                ingrediant.append(ingrediantString.get(i) + "\n");
            }
        }

    }
}
